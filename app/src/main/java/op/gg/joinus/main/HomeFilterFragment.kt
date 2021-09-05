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

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        setToolbar()
        setBottomNavigationView()
        super.onStart()
    }

    override fun onStop() {
        returnToolbar()
        returnBottomNavigationView()
        super.onStop()
    }

    private fun setBottomNavigationView(){
        val bottomNavigationView = (activity as MainActivity).getBinding().bottomNavigationViewMain
        bottomNavigationView.visibility = View.GONE
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setToolbar(){
        val toolbar = (activity as MainActivity).getBinding().toolbarMain
        (activity as MainActivity).getBinding().toolbarMainTitle.text = "매칭 필터"
        (activity as MainActivity).getBinding().toolbarMainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_toolbar_navigation,resources.newTheme())
        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }

    }

    private fun returnToolbar(){
        val toolbar = (activity as MainActivity).getBinding().toolbarMain
        toolbar.navigationIcon = null
        toolbar.setNavigationOnClickListener {  }
        toolbar.menu.clear()
        (activity as MainActivity).getBinding().toolbarMainTitle.text =""
    }
    private fun returnBottomNavigationView(){
        val bottomNavigationView = (activity as MainActivity).getBinding().bottomNavigationViewMain
        bottomNavigationView.visibility = View.VISIBLE
    }

}