package op.gg.joinus.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentMyBinding

class MyFragment : Fragment() {
    private lateinit var binding: FragmentMyBinding
    private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_my,
                container,
                false
            )

        return binding.root
    }

    private fun initView() {
        binding.btnFriendsList.setOnClickListener {  }
        binding.btnFriendsList.setOnClickListener {  }
        binding.btnFriendsList.setOnClickListener {  }
    }

    private fun replaceFragment() {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onStart() {
        (activity as MainActivity).resetToolbar()
        setToolbar()
        super.onStart()
    }

    override fun onStop() {
        (activity as MainActivity).resetToolbar()
        super.onStop()
    }

    private fun setToolbar() {
        val menuListener = Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_logout -> {
                    //TODO: 로그아웃 다이얼로그 생성성
                }
            }
            true
        }
        (activity as MainActivity).setToolbar(R.menu.menu_home, menuListener)
    }
}