package op.gg.joinus.chat

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.bishoybasily.stomp.lib.Event
import com.gmail.bishoybasily.stomp.lib.StompClient
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import okhttp3.OkHttpClient
import op.gg.joinus.R
import op.gg.joinus.databinding.*
import op.gg.joinus.main.MainActivity
import op.gg.joinus.model.ChatData
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.model.RoomUpdate
import op.gg.joinus.model.UserInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.SharedPreferenceManager
import op.gg.joinus.util.joinLog
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.net.URISyntaxException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates

class ChatActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChatBinding
    private lateinit var roomInfo: RoomInfo
    private var userPk by Delegates.notNull<Int>()
    private val adapter = ChattingAdapter()
    private val chatList = mutableListOf<ChattingItem>()
    private lateinit var userInfo: UserInfo
    private lateinit var db:FirebaseFirestore
    private lateinit var collection:CollectionReference


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        binding.activityChat = this
        userPk = SharedPreferenceManager.getInt(this,"pk")
        loadRoomInfo()
    }

    private fun loadRoomInfo(){
        val roomPk = intent.getIntExtra("room_pk",-1)
        intent.removeExtra("room_pk")
        if(roomPk != -1){
            val retrofit = RetrofitClient.getInstance()
            val api = retrofit.buildRetrofit()
            val callGetRoomInfo = api.getRoomInfo(roomPk)
            callGetRoomInfo.enqueue(object: Callback<RoomInfo> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(call: Call<RoomInfo>, response: Response<RoomInfo>) {
                    if(!response.isSuccessful){
                        joinLog("response err",response.body().toString())
                    }
                    joinLog("response",response.body().toString())
                    if(response.body() != null){
                        roomInfo = response.body()!!
                        setToolbar()
                        initView()
                        initFireBase()
                    }
                }
                override fun onFailure(call: Call<RoomInfo>, t: Throwable) {
                    joinLog("response fail",t.toString())
                }
            })
        }
    }

    private fun initFireBase(){
        db = Firebase.firestore
        //collection = db.collection("JoinusChatting")
        //receiveDB()
    }

    private fun receiveDB(){
        collection.document(roomInfo.pk.toString()).collection("chat")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    joinLog("receive message", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                joinLog("Error getting documents.", exception.toString())
            }
    }

    private fun setDBdata(ampmTime: String, sendTime: String, message: String) {
        val data = hashMapOf(
            userInfo.pk to userInfo.nickname,
            "message" to message,
            "time" to ampmTime
        )
        collection.document(roomInfo.pk.toString()).collection("chat").document(sendTime).set(data)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendMessage(){
        val body = binding.etxtMessage.text.toString()
        if (body !=""){
            joinLog("sendMessage",body)
        }
        val sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        var ampmTime=""
        val timeList = sendTime.split(":")
        if(timeList[0].toInt() >= 12){
            ampmTime += "PM "
            if(timeList[0].toInt() == 12){
                ampmTime += timeList[0] +  ":" +timeList[1]
            }
            else{
                ampmTime += (timeList[0].toInt()-12).toString() +  ":" +timeList[1]
            }
        }
        else{
            ampmTime += "AM " + timeList[0] + ":" +timeList[1]
        }


        setDBdata(ampmTime,sendTime,body)
        binding.etxtMessage.text.clear()
    }

    private fun receiveMessage(){
        val roomPk = roomInfo.pk
    }


    private var receiveChat = Emitter.Listener{ args->
        joinLog("receiveChat",args.toString())
        val obj = JSONObject(args[0].toString())
        val chatData = ChatData(obj.getString("user_name"),obj.getString("content"),obj.getInt("room_pk"),obj.getInt("is_ban"),obj.getString("target_name"))
        /*
        userInfo:UserInfo,
        sendTime:String,
        sendType:Boolean,
        sendBody:String
         */
        val userList = roomInfo.user_list.iterator()
        val sendUserInfo:UserInfo
        while(userList.hasNext()){
            val tempUserInfo = userList.next()
            if(chatData.user_name == tempUserInfo.nickname){
                sendUserInfo = tempUserInfo
                chatList.add(ChattingItem(sendUserInfo,"",userInfo.pk == sendUserInfo.pk,chatData.content))
                adapter.notifyDataSetChanged()
                break
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initView(){
        val userListIt = roomInfo.user_list.iterator()
        while(userListIt.hasNext()){
            val tempUser = userListIt.next()
            if(tempUser.pk == userPk){
                userInfo = tempUser
            }
        }
        binding.rcChattingRoom.layoutManager = LinearLayoutManager(this)
        adapter.chattingList = chatList
        binding.rcChattingRoom.adapter = adapter

        binding.btnSendChat.setOnClickListener {
            sendMessage()
        }
        binding.toolbarChatBtnManner.setOnClickListener {
            val builder = Dialog(this)
            val bindingDialog: DialogCheckMannerBinding = DataBindingUtil.inflate(
                LayoutInflater.from(
                    this
                ), R.layout.dialog_check_manner, null, false
            )
            bindingDialog.btnCheckManner.setOnClickListener {
                builder.dismiss()
            }
            builder.setContentView(bindingDialog.root)
            builder.show()
        }
        if (roomInfo.leader_pk == userPk){
            binding.layoutHostGameStart.visibility = View.VISIBLE
        }
        binding.btnStartGame.setOnClickListener {
            val builder = Dialog(this)
            val bindingDialog: DialogChatStartGameBinding = DataBindingUtil.inflate(
                LayoutInflater.from(
                    this
                ), R.layout.dialog_chat_start_game, null, false
            )
            bindingDialog.btnChatCancelGame.setOnClickListener {
                builder.dismiss()
            }
            bindingDialog.btnChatStartGame.setOnClickListener {
                roomInfo.is_start = 1
                val roomUp = RoomUpdate(roomInfo.game_name,roomInfo.highest_tier,1,roomInfo.leader_pk,roomInfo.lowest_tier,roomInfo.people_number,roomInfo.pk,roomInfo.room_name,roomInfo.start_date,roomInfo.voice_chat)
                val retrofit = RetrofitClient.getInstance()
                val api = retrofit.buildRetrofit()
                val callPutRoom = api.putRoom(roomUp)
                callPutRoom.enqueue(object: Callback<Int> {
                    override fun onResponse(call: Call<Int>, response: Response<Int>) {
                        if(!response.isSuccessful){
                            joinLog("response err",response.body().toString())
                        }
                        else{
                            joinLog("response",response.body().toString())
                            setStartGameView()
                        }
                    }
                    override fun onFailure(call: Call<Int>, t: Throwable) {
                        joinLog("response fail",t.toString())
                    }
                })
                builder.dismiss()
            }
            builder.setContentView(bindingDialog.root)
            builder.show()
        }
        if(roomInfo.is_start == 1){
            setStartGameView()
        }
    }

    fun setStartGameView(){
        binding.frChattingBar.visibility = View.GONE
        binding.frGameComplete.visibility = View.VISIBLE
        binding.layoutHostGameStart.visibility = View.GONE
        if (roomInfo.leader_pk != userPk){
            binding.btnGameComplete.visibility = View.GONE
        }
        else{
            binding.btnGameComplete.setOnClickListener {
                val i = Intent(this, MannerActivity::class.java)
                i.putExtra("room_pk",roomInfo.pk)
                startActivity(i)
            }
        }
    }

    fun setToolbar(){
        val toolbar = binding.toolbarChat
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_navigation)
        toolbar.setNavigationOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        toolbar.inflateMenu(R.menu.menu_chat)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.item_chat_show_people->{
                    setItemShowPeople()
                }
                R.id.item_chat_show_action ->{
                    setItemShowAction()
                }
            }
            return@setOnMenuItemClickListener true
        }
        binding.toolbarChatRoomTitle.text = roomInfo.room_name
        if(roomInfo.room_manner>= -5){
            binding.toolbarChatBtnManner.setBackgroundResource(R.drawable.ic_manner_1)
        }
        else if(roomInfo.room_manner>=0){
            binding.toolbarChatBtnManner.setBackgroundResource(R.drawable.ic_manner_2)
        }
        else if(roomInfo.room_manner>=5){
            binding.toolbarChatBtnManner.setBackgroundResource(R.drawable.ic_manner_3)
        }
        else{
            binding.toolbarChatBtnManner.setBackgroundResource(R.drawable.ic_manner_4)
        }



    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setItemShowPeople(){
        val builder = Dialog(this)
        val bindingDialog: DialogChatPeopleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                this
            ), R.layout.dialog_chat_people, null, false
        )

        bindingDialog.btnExitChatPeople.setOnClickListener {
            builder.dismiss()
        }
        bindingDialog.rcChatPeople.layoutManager = LinearLayoutManager(this)

        val adapter = ChatRoomDialogAdapter()
        val itemList = mutableListOf<ChatRoomItem>()
        val userIt = roomInfo.user_list.iterator()

        while (userIt.hasNext()){
            val tempUser = userIt.next()
            val tempItem = ChatRoomItem(roomInfo.leader_pk,tempUser,userPk)
            itemList.add(tempItem)
        }
        adapter.itemList = itemList.toList()
        adapter.notifyDataSetChanged()
        bindingDialog.rcChatPeople.adapter = adapter
        builder.setContentView(bindingDialog.root)
        builder.show()
    }
    private fun setItemShowAction(){
        val builder = Dialog(this)
        builder.window?.attributes?.width = WindowManager.LayoutParams.MATCH_PARENT
        val bindingDialog: DialogChatOptionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                this
            ), R.layout.dialog_chat_option, null, false
        )

        bindingDialog.btnChatOptionCancel.setOnClickListener {
            builder.dismiss()
        }
        bindingDialog.btnChatOptionExit.setOnClickListener {
            builder.dismiss()
        }
        bindingDialog.btnChatOptionUpdate.setOnClickListener {
            builder.dismiss()
        }
        builder.setContentView(bindingDialog.root)
        builder.show()
    }
}
