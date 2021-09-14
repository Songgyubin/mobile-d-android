package op.gg.joinus.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.ItemHomeRoomlistBinding

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
        val binding = ItemHomeRoomlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
    inner class MyViewHolder(private val binding:ItemHomeRoomlistBinding) : RecyclerView.ViewHolder(binding.root){
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
        fun getBinding():ItemHomeRoomlistBinding{
            return binding
        }
    }

}