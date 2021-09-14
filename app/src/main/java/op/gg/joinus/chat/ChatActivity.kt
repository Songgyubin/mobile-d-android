package op.gg.joinus.chat

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityChatBinding
import op.gg.joinus.databinding.DialogChatPeopleBinding
import op.gg.joinus.databinding.DialogCheckMatchingBinding
import op.gg.joinus.main.MainActivity
import op.gg.joinus.model.ChatData
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.model.UserInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.joinLog
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URISyntaxException

class ChatActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChatBinding
    private lateinit var roomInfo: RoomInfo
    private val socketUrl = "http://3.128.67.103/"
    //private val socketUrl = "http://ec2-3-128-67-103.us-east-2.compute.amazonaws.com:8080/socket/"
    private val adapter = ChattingAdapter()
    private val chatList = mutableListOf<ChattingItem>()
    private lateinit var mSocket: Socket
    private lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        binding.activityChat = this
        loadRoomInfo()
    }

    private fun loadRoomInfo(){
        val roomPk = intent.getIntExtra("room_pk",-1)
        if(roomPk != -1){
            val retrofit = RetrofitClient.getInstance()
            val api = retrofit.buildRetrofit()
            val callGetRoomInfo = api.getRoomInfo(roomPk)
            callGetRoomInfo.enqueue(object: Callback<RoomInfo> {
                override fun onResponse(call: Call<RoomInfo>, response: Response<RoomInfo>) {
                    if(!response.isSuccessful){
                        joinLog("response err",response.body().toString())
                    }
                    joinLog("response",response.body().toString())
                    if(response.body() != null){
                        roomInfo = response.body()!!
                        setToolbar()
                        initSocket()
                        initView()
                    }
                }
                override fun onFailure(call: Call<RoomInfo>, t: Throwable) {
                    joinLog("response fail",t.toString())
                }
            })
        }
    }

    fun initSocket(){
        try{
            mSocket = IO.socket(socketUrl)
        }catch (e: URISyntaxException){
            e.printStackTrace()
        }
        mSocket.connect()
        joinLog("socket connect" , "${mSocket.connected()} : " + mSocket.id())
        receiveMessage()
    }

    fun sendMessage(){
        val roomPk = roomInfo.pk
        //? 임시 추가 나중에 삭제 필요
        val userPk = 1
        val userListIt = roomInfo.user_list.iterator()
        while(userListIt.hasNext()){
            val tempUser = userListIt.next()
            if(tempUser.pk == userPk){
                userInfo = tempUser
            }
        }
        //? 삭제필요
        val body = binding.etxtMessage.text.toString()

        mSocket.emit("receive/$roomPk",ChatData(userInfo.nickname,body,roomPk,0,"") )

        binding.etxtMessage.text.clear()
    }

    fun receiveMessage(){
        val roomPk = roomInfo.pk
        mSocket.on("send/$roomPk",receiveChat)

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

    fun initView(){
        binding.rcChattingRoom.layoutManager = LinearLayoutManager(this)
        adapter.chattingList = chatList
        binding.rcChattingRoom.adapter = adapter

        binding.btnSendChat.setOnClickListener {
            sendMessage()
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
        //+ user_pk 부분 불러와서 추가할것
        val userPk = 1

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

    }
}