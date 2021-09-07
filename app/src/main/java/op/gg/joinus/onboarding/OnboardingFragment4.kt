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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_4, container, false)
        binding.btnConfirm.setOnClickListener {
            (activity as OnboardingActivity).replaceFragment(4)
        }
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        joinLog(TAG,"onResume")
    }
    companion object {
        private const val TAG = "OnboardingFragment4"
    }
}