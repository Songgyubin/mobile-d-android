package op.gg.joinus.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentFriendsListBinding
import op.gg.joinus.model.UserInfo

class FriendsListFragment : Fragment() {
    private lateinit var binding: FragmentFriendsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_friends_list,
                container,
                false
            )
        val items = mutableListOf<UserInfo>()

        items.add(UserInfo(nickname = "미드만가요"))
        items.add(UserInfo(nickname = "정글 안 주면 던짐"))
        items.add(UserInfo(nickname = "opgg 최고"))
        items.add(UserInfo(nickname = "스티븐잡스"))
        items.add(UserInfo(nickname = "아이패드"))

        with(binding.recyclerFriendsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = FriendsListAdapter(items)
            addItemDecoration(DividerItemDecoration(context,1))
        }

        return binding.root
    }

}