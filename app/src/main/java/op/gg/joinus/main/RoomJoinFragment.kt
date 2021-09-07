package op.gg.joinus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentRoomJoinBinding

class RoomJoinFragment(item:HomeRoomListItem) : Fragment() {
    private lateinit var binding: FragmentRoomJoinBinding
    private val item:HomeRoomListItem = item
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //data bind
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_join,container,false)
        // set roomTitle
        binding.txtDialogRoomJoinTitle.text = item.title
        // set room Game Img
        Glide.with(binding.imgDialogRoomJoinGame.context).load(item.imgGame).into(binding.imgDialogRoomJoinGame)
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
        var tier:String = ""
        when(item.room.game_name){
            "lol"->{
                when(item.room.lowest_tier){
                    0->{
                        tier +="아이언"
                    }
                    1->{
                        tier +="브론즈"
                    }
                    2->{
                        tier +="실버"
                    }
                    3->{
                        tier +="골드"
                    }
                    4->{
                        tier +="플래티넘"
                    }
                    5->{
                        tier +="다이아"
                    }
                    6->{
                        tier +="마스터"
                    }
                }
                tier += " 이상 "
                when(item.room.highest_tier){
                    0->{
                        tier +="아이언"
                    }
                    1->{
                        tier +="브론즈"
                    }
                    2->{
                        tier +="실버"
                    }
                    3->{
                        tier +="골드"
                    }
                    4->{
                        tier +="플래티넘"
                    }
                    5->{
                        tier +="다이아"
                    }
                    6->{
                        tier +="마스터"
                    }
                }
                tier += " 이하"
            }
        }
        binding.txtDialogRoomJoinTier.text = tier
        // set Start Date/Time
        val date = item.room.start_date.year.toString() + "년 " + item.room.start_date.month.toString() + "월 " + item.room.start_date.day.toString() + "일 " + item.room.start_date.hours.toString() + "시 " + item.room.start_date.minutes.toString() + "분"
        binding.txtDialogRoomJoinTime.text = date
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        binding.rcDialogRoomJoin.layoutManager = layoutManager
        val userListAdapter = UserJoinListAdapter(requireContext())
        userListAdapter.itemList = item.room.user_list
        binding.rcDialogRoomJoin.adapter = userListAdapter
        return binding.root
    }

}