package op.gg.joinus.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding3Binding
import op.gg.joinus.databinding.FragmentOnboarding4Binding
import op.gg.joinus.util.joinLog

class OnboardingFragment4 : Fragment() {
    private lateinit var binding: FragmentOnboarding4Binding
    private lateinit var onboardActivity: OnboardingActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_4, container, false)
        onboardActivity = activity as OnboardingActivity
        binding.btnConfirm.setOnClickListener {
            (activity as OnboardingActivity).replaceFragment(4)
        }
        initRadioButton()
        onboardActivity.setVisibleGameLogo(true)
        return binding.root
    }

    private fun initRadioButton() {
        binding.rgTier.setOnCheckedChangeListener { group, checkedId ->
            (activity as OnboardingActivity).gameTier =
                when (checkedId) {
                    R.id.rb_tier_iron -> "iron"
                    R.id.rb_tier_bronze -> "bronze"
                    R.id.rb_tier_silver -> "silver"
                    R.id.rb_tier_gold -> "gold"
                    R.id.rb_tier_platinum -> "platinum"
                    R.id.rb_tier_diamond -> "diamond"
                    R.id.rb_tier_master -> "master"
                    R.id.rb_tier_challenger -> "challenger"
                    else -> ""
                }
            binding.btnConfirm.isEnabled = true
        }
    }

    override fun onResume() {
        super.onResume()
        joinLog(TAG, "onResume")
    }

    companion object {
        private const val TAG = "OnboardingFragment4"
    }
}