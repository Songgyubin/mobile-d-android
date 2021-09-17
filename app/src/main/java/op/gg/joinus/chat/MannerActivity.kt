package op.gg.joinus.chat

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityMannerBinding
import op.gg.joinus.main.MainActivity
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.SharedPreferenceManager
import op.gg.joinus.util.joinLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class MannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMannerBinding
    private lateinit var roomInfo:RoomInfo
    private lateinit var adapter:MannerAdapter
    private val mannerList = mutableListOf<MannerItem>()
    private var userPk by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manner)
        binding.activityManner = this
        userPk = SharedPreferenceManager.getInt(this,"pk")
        adapter = MannerAdapter()
        loadRoomInfo()
    }

    private fun initView(){

        binding.rcMannerUser.layoutManager = LinearLayoutManager(this)
        for (user in roomInfo.user_list){
            if(userPk != user.pk){
                mannerList.add(MannerItem(user))
            }
        }
        adapter.userList = mannerList
        binding.rcMannerUser.adapter = adapter
    }

    private fun initListener(){
        binding.btnMannerOk.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        binding.btnMannerCancel.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun loadRoomInfo(){
        val roomPk = intent.getIntExtra("room_pk",-1)
        intent.removeExtra("room_pk")
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
                        initView()
                        initListener()
                    }
                }
                override fun onFailure(call: Call<RoomInfo>, t: Throwable) {
                    joinLog("response fail",t.toString())
                }
            })
        }
    }
}