package op.gg.joinus.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding2Binding
import op.gg.joinus.databinding.FragmentOnboarding3Binding
import op.gg.joinus.util.joinLog

class OnboardingFragment3 : Fragment() {
    private lateinit var binding: FragmentOnboarding3Binding
    private lateinit var onboardActivity: OnboardingActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_3, container, false)
        onboardActivity = activity as OnboardingActivity
        initButton()

        return binding.root
    }

    private fun initButton() {
        binding.btnConfirm.setOnClickListener {
            onboardActivity.nickName = binding.edId.text.toString()
            (activity as OnboardingActivity).replaceFragment(3)
        }
    }

    override fun onResume() {
        super.onResume()
        joinLog(TAG, "onResume")
    }

    companion object {
        private const val TAG = "OnBoardingFragment3"
    }
}