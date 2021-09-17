package op.gg.joinus.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.ItemMannerUserBinding

class MannerAdapter() : RecyclerView.Adapter<MannerAdapter.MyViewHolder>(){
    var userList = listOf<MannerItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MannerAdapter.MyViewHolder {
        val binding = ItemMannerUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MannerAdapter.MyViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class MyViewHolder(private val binding: ItemMannerUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: MannerItem){
            with(binding){
                itemMannerUser = item
                executePendingBindings()

            }
        }
    }
}