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
import op.gg.joinus.databinding.FragmentAddMatchingBinding
import java.util.*

class AddMatchingFragment: Fragment() {
    private lateinit var binding:FragmentAddMatchingBinding
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_matching,container,false)
        setToolbar()
        setBottomNavigationView()
        initView()
        return binding.root
    }

    override fun onDestroy() {
        returnToolbar()
        returnBottomNavigationView()
        super.onDestroy()
    }

    private fun returnToolbar(){
        val toolbar = (activity as MainActivity).getBinding().toolbarMain
        toolbar.navigationIcon = null
        toolbar.setNavigationOnClickListener {  }
        (activity as MainActivity).getBinding().toolbarMainTitle.text =""
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setToolbar(){
        val toolbar = (activity as MainActivity).getBinding().toolbarMain
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_toolbar_navigation_exit,resources.newTheme())
        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).supportFragmentManager.popBackStack()
        }
        (activity as MainActivity).getBinding().toolbarMainTitle.text = "매칭 만들기"
        (activity as MainActivity).getBinding().toolbarMainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)

    }
    private fun setBottomNavigationView(){
        val bottomNavigationView = (activity as MainActivity).getBinding().bottomNavigationViewMain
        bottomNavigationView.visibility = View.GONE
    }

    private fun returnBottomNavigationView(){
        val bottomNavigationView = (activity as MainActivity).getBinding().bottomNavigationViewMain
        bottomNavigationView.visibility = View.VISIBLE
    }

    private fun initView(){
        // set StartDate
        val calendar = Calendar.getInstance()
        val startDate = calendar.get(Calendar.YEAR).toString() + "년 " +
                calendar.get(Calendar.MONTH).toString() + "월 " +
                calendar.get(Calendar.DAY_OF_MONTH).toString() + "일 " +
                calendar.get(Calendar.HOUR).toString() + "시 " +
                calendar.get(Calendar.MINUTE).toString() + "분"
        binding.txtStartDate.text = startDate
        // set voice
        binding.rbVoiceYes.isChecked = true
        //
    }
}