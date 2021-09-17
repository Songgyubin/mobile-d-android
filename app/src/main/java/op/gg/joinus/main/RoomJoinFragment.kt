package op.gg.joinus.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import op.gg.joinus.R
import op.gg.joinus.chat.ChatActivity
import op.gg.joinus.databinding.FragmentRoomJoinBinding
import op.gg.joinus.model.RoomInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.SharedPreferenceManager
import op.gg.joinus.util.getTier
import op.gg.joinus.util.joinLog
import op.gg.joinus.util.setImg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RoomJoinFragment(private val item: HomeRoomListItem) : Fragment() {
    private lateinit var binding: FragmentRoomJoinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //data bind
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_join,container,false)

        // initialize View
        initView()
        // set button click listener
        setButton()

        return binding.root
    }

    override fun onStart() {
        (activity as MainActivity).resetToolbar()
        setToolbar()
        super.onStart()
    }

    override fun onStop() {
        (activity as MainActivity).resetToolbar()
        super.onStop()
    }

    private fun setToolbar(){
        (activity as MainActivity).setToolbar("매칭 참가",R.drawable.ic_toolbar_navigation)
    }

    private fun initView(){
        // set roomTitle
        binding.txtDialogRoomJoinTitle.text = item.title
        // set room Game Img
        setImg(binding.imgDialogRoomJoinGame.context,item.imgGame,binding.imgDialogRoomJoinGame)
        // set room numOf People
        binding.txtDialogRoomJoinPeople.text = (item.numPeople + " 명")
        // set able Voice
        if (item.room.voice_chat){
            binding.txtDialogRoomJoinVoice.text = "가능"
        }
        else{
            binding.txtDialogRoomJoinVoice.text = "불가능"
        }
        // set tier
        val tier:String = getTier(item.room.game_name,item.room.lowest_tier) + " 이상 " + getTier(item.room.game_name,item.room.highest_tier) + " 이하"
        binding.txtDialogRoomJoinTier.text = tier
        // set Start Date/Time
        var date = ""
        try{
            val day = item.room.start_date.split(" ")[0].split("-")
            val time = item.room.start_date.split(" ")[1].split(":")
            date = day[0] + "년 " +
                    day[1] + "월 " +
                    day[2] + "일 " +
                    time[0] + "시 " +
                    time[1] + "분 "
        }catch(e:IOException){
            joinLog("HomeRoomListItem",e.stackTraceToString())
        }
        binding.txtDialogRoomJoinTime.text = date
        // set Joining user
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        binding.rcDialogRoomJoin.layoutManager = layoutManager
        val userListAdapter = UserJoinListAdapter(requireContext())
        userListAdapter.itemList = item.room.user_list
        binding.rcDialogRoomJoin.adapter = userListAdapter
    }

    private fun setButton(){
        binding.btnDialogRoomJoin.setOnClickListener {
            if(SharedPreferenceManager.getInt(requireContext(),"pk") != 0){
                var searchFlag = false
                for (i in item.room.user_list){
                    if(SharedPreferenceManager.getInt(requireContext(),"pk") == i.pk){
                        searchFlag = true
                        break
                    }
                }
                var endFlag = false
                if (item.room.people_number == item.room.now_people_cnt){
                    endFlag = true
                }
                if(searchFlag){
                    val i = Intent(context,ChatActivity::class.java)
                    i.putExtra("room_pk",item.room.pk)
                    startActivity(i)
                    joinLog("RoomJoin","alreadyJoin")
                }
                else{
                    if(!endFlag){
                        val retrofit = RetrofitClient.getInstance()
                        val api = retrofit.buildRetrofit()
                        val hashMap = HashMap<String,Int>()
                        hashMap.put("room_pk",item.room.pk)
                        hashMap.put("user_pk",SharedPreferenceManager.getInt(requireContext(),"pk"))
                        val callPostRoomUser = api.postRoomUser(hashMap)
                        callPostRoomUser.enqueue(object: Callback<Int> {
                            @SuppressLint("NotifyDataSetChanged")
                            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                if(!response.isSuccessful){
                                    joinLog("response err",response.body().toString())
                                    Toast.makeText(requireContext(),"현재 매칭방에 입장이 불가능합니다. 인터넷 연결을 확인해주세요.",Toast.LENGTH_SHORT).show()
                                }
                                else{
                                    joinLog("response",response.body().toString())
                                    val i = Intent(context,ChatActivity::class.java)
                                    i.putExtra("room_pk",item.room.pk)
                                    startActivity(i)
                                }
                            }
                            override fun onFailure(call: Call<Int>, t: Throwable) {
                                joinLog("response fail",t.toString())
                                Toast.makeText(requireContext(),"현재 매칭방에 입장이 불가능합니다. 인터넷 연결을 확인해주세요.",Toast.LENGTH_SHORT).show()
                            }
                        })

                    }
                    else{
                        Toast.makeText(requireContext(),"현재 매칭방이 꽉차서 입장이 불가능 합니다.",Toast.LENGTH_SHORT).show()
                        joinLog("RoomJoin","failJoin")
                    }
                }

            }
            else{
                joinLog("RoomJoin","fail load pk : " +SharedPreferenceManager.getInt(requireContext(),"pk").toString())
            }
        }

    }
}