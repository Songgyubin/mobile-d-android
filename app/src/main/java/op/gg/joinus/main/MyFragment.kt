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
import op.gg.joinus.util.getTier

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
        initView()
        return binding.root
    }

    private fun initView() {

        binding.btnFriendsList.setOnClickListener { replaceFragment(FriendsListFragment()) }
        (activity as MainActivity).resetToolbar()
        setToolbar()
    }

    private fun replaceFragment(fragment: Fragment) {
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .add((activity as MainActivity).binding.fragmentContainerViewMain.id, fragment)
            .commit()
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
                R.id.item_logout -> { }
            }
            true
        }
//        (activity as MainActivity).setToolbar("MY",R.menu.menu_home, menuListener)

        (activity as MainActivity).setToolbar(R.menu.menu_my, menuListener, "MY")
    }

}