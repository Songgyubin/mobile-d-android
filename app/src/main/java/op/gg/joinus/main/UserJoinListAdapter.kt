package op.gg.joinus.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.ItemUserJoinBinding
import op.gg.joinus.model.UserInfo

class UserJoinListAdapter(context: Context):RecyclerView.Adapter<UserJoinListAdapter.MyViewHolder>() {
    var itemList = listOf<UserInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserJoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    inner class MyViewHolder(private val binding: ItemUserJoinBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:UserInfo){
            with(binding){
                itemUserJoin = item
                executePendingBindings()
            }
        }
    }
}