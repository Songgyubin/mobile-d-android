package op.gg.joinus.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.ItemChatRoomPeopleBinding


class ChatRoomDialogAdapter() : RecyclerView.Adapter<ChatRoomDialogAdapter.MyViewHolder>()  {
    var itemList = listOf<ChatRoomItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemChatRoomPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatRoomDialogAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    inner class MyViewHolder(private val binding: ItemChatRoomPeopleBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ChatRoomItem){
            with(binding){
                chatRoomPeople = item
                executePendingBindings()
            }
        }
    }
}