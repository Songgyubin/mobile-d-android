package op.gg.joinus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room_join,container,false)
        binding.txtDialogRoomJoinTitle.text = item.title
        Glide.with(binding.imgDialogRoomJoinGame.context).load(item.imgGame).into(binding.imgDialogRoomJoinGame)
        binding.txtDialogRoomJoinPeople.text = item.numPeople


        return binding.root
    }

}