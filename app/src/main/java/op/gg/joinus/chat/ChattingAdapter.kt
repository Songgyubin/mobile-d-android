package op.gg.joinus.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.ItemChattingRoomBinding

class ChattingAdapter() : RecyclerView.Adapter<ChattingAdapter.MyViewHolder>(){
    var chattingList = listOf<ChattingItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChattingAdapter.MyViewHolder {
        val binding = ItemChattingRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChattingAdapter.MyViewHolder, position: Int) {
        holder.bind(chattingList[position])
    }

    override fun getItemCount(): Int {
        return chattingList.size
    }

    inner class MyViewHolder(private val binding: ItemChattingRoomBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ChattingItem){
            with(binding){
                itemChattingRoom = item
                executePendingBindings()
            }
        }
    }
}