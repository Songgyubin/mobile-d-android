package op.gg.joinus.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import op.gg.joinus.databinding.ItemFriendsListBinding
import op.gg.joinus.model.UserInfo

class FriendsListAdapter(private val items: MutableList<UserInfo>) :
    RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsListAdapter.FriendsViewHolder {
        val binding =
            ItemFriendsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsListAdapter.FriendsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size


    inner class FriendsViewHolder(private val binding: ItemFriendsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserInfo) {
            with(binding) {
                itemFriends = item
                executePendingBindings()
            }
        }
    }
}