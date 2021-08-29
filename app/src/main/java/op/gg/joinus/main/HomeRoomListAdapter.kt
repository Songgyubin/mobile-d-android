package op.gg.joinus.main

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.R
import op.gg.joinus.databinding.HomeRoomlistItemBinding

class HomeRoomListAdapter(private val context : Context) : RecyclerView.Adapter<HomeRoomListAdapter.MyViewHolder>() {

    var itemList = listOf<HomeRoomListItem>()
    private var mListener:OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClick(item:HomeRoomListItem)
    }
    fun setOnItemClickListner(listener:OnItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRoomListAdapter.MyViewHolder {
        val binding = HomeRoomlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    inner class MyViewHolder(private val binding:HomeRoomlistItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:HomeRoomListItem){
            with(binding){
                homeRoomlistItem = item
                layoutHomeRoomlistItem.setOnClickListener{
                    if(mListener != null){
                        mListener!!.onItemClick(item)
                    }
                }
                executePendingBindings()
            }
        }
        fun getBinding():HomeRoomlistItemBinding{
            return binding
        }
    }

}