package op.gg.joinus.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentChangeTierBinding
import op.gg.joinus.util.getTier
import op.gg.joinus.util.joinLog
import java.util.*

// 임시로 이전 fragment 를 매개변수로 받음 (수정사항)
class ChangeTierFragment(private val gameName:String, private val fragment:AddMatchingFragment) : Fragment() {
    private lateinit var binding:FragmentChangeTierBinding
    private var highestTier: Int = 0
    private var lowestTier: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_tier,container,false)
        initView()
        initListener()
        return binding.root
    }

    private fun initView(){
        highestTier = 0
        lowestTier = 0
        binding.rbHighTier0.isChecked = true
        binding.rbLowTier0.isChecked = true
        setRadioButtonText(gameName)
    }

    private fun initListener(){
        val rgLowListener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            for(i in 0 until group.size){
                if (group.getChildAt(i).id == checkedId){
                    lowestTier=i
                    break
                }
            }
        }
        val rgHighListener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            for(i in 0 until group.size){
                if (group.getChildAt(i).id == checkedId){
                    highestTier=i
                    break
                }
            }
        }
        binding.rgHighTier.setOnCheckedChangeListener(rgHighListener)
        binding.rgLowTier.setOnCheckedChangeListener(rgLowListener)
    }


    override fun onStart() {
        (activity as MainActivity).resetToolbar()
        setToolbar()
        super.onStart()
    }

    override fun onStop(){
        (activity as MainActivity).resetToolbar()
        super.onStop()
    }

    override fun onDestroy() {
        fragment.setToolbar()
        super.onDestroy()
    }

    private fun setToolbar(){
        val menuListener = Toolbar.OnMenuItemClickListener { item ->
            val addMatchingFragment = fragment
            when(item.itemId){
                R.id.item_add_match_ok->{
                    if(lowestTier == 0 || highestTier == 0){
                        addMatchingFragment.getBinding().txtTier.text = "모두 가능"
                    }
                    else{
                        addMatchingFragment.getBinding().txtTier.text = (getTier(gameName,lowestTier-1)+" 이상 " + getTier(gameName,highestTier-1) + " 이하")
                    }
                }
            }
            (activity as MainActivity).supportFragmentManager.popBackStack()
            true
        }
        (activity as MainActivity).setToolbar(R.menu.menu_add_matching,menuListener,"참가자 티어",R.drawable.ic_toolbar_navigation)
    }

    private fun setRadioButtonText(gameName: String){
        when(gameName){
            "league of legends" ->{
                binding.rbLowTier0.text = "모두 가능"
                binding.rbHighTier0.text = "모두 가능"
                var tierStr = "아이언"
                binding.rbLowTier1.text = ("$tierStr 이상")
                binding.rbHighTier1.text = ("$tierStr 이하")
                tierStr = "브론즈"
                binding.rbLowTier2.text = ("$tierStr 이상")
                binding.rbHighTier2.text = ("$tierStr 이하")
                tierStr = "실버"
                binding.rbLowTier3.text = ("$tierStr 이상")
                binding.rbHighTier3.text = ("$tierStr 이하")
                tierStr = "골드"
                binding.rbLowTier4.text = ("$tierStr 이상")
                binding.rbHighTier4.text = ("$tierStr 이하")
                tierStr = "플래티넘"
                binding.rbLowTier5.text = ("$tierStr 이상")
                binding.rbHighTier5.text = ("$tierStr 이하")
                tierStr = "다이아"
                binding.rbLowTier6.text = ("$tierStr 이상")
                binding.rbHighTier6.text = ("$tierStr 이하")
                tierStr = "마스터"
                binding.rbLowTier7.text = ("$tierStr 이상")
                binding.rbHighTier7.text = ("$tierStr 이하")
                tierStr = "챌린저"
                binding.rbLowTier8.text = ("$tierStr 이상")
                binding.rbHighTier8.text = ("$tierStr 이하")
            }
        }
    }
}