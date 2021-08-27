package op.gg.joinus.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.HomeRoomlistItemBinding
import org.json.JSONException

class HomeRoomListAdapter(private val context : Context) : RecyclerView.Adapter<HomeRoomListAdapter.MyViewHolder>() {

    var itemList = listOf<HomeRoomListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRoomListAdapter.MyViewHolder {
        val binding = HomeRoomlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.getBinding().layoutHomeRoomlistItem.setOnClickListener {
            Log.d("RoomListItemClick",itemList[position].title)
        }

    }
    inner class MyViewHolder(private val binding:HomeRoomlistItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeRoomListItem){
            with(binding){
                homeRoomlistItem = item
                executePendingBindings()
            }
        }
        fun getBinding():HomeRoomlistItemBinding{
            return binding
        }
    }

}