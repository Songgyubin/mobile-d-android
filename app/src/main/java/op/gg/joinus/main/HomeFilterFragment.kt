package op.gg.joinus.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentHomeFilterBinding

class HomeFilterFragment: Fragment() {
    private lateinit var binding:FragmentHomeFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_filter,container,false)
        initListener()
        return binding.root
    }

    override fun onStart() {
        (activity as MainActivity).resetToolbar()
        setToolbar()
        (activity as MainActivity).setBottomNavigationView()
        super.onStart()
    }

    override fun onStop() {
        (activity as MainActivity).resetToolbar()
        (activity as MainActivity).returnBottomNavigationView()
        super.onStop()
    }

    fun setToolbar(){
        (activity as MainActivity).setToolbar("매칭 필터",R.drawable.ic_toolbar_navigation)
    }
    private fun initListener(){
        binding.btnChangeTier.setOnClickListener {
            var gameName = "league of legends"

            (activity as MainActivity).resetToolbar()
            val changeTierFragment = ChangeTierFragment(gameName,binding.txtTier)
            changeTierFragment.toolbarSet = object : ChangeTierFragment.ToolbarSetting{
                override fun toolbarSet(){
                    setToolbar()
                }
            }
            parentFragmentManager.beginTransaction()
                .hide(this)
                .add(R.id.fragmentContainerView_main,changeTierFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}