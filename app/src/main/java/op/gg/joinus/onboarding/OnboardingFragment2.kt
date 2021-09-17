package op.gg.joinus.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding2Binding
import op.gg.joinus.util.joinLog

class OnboardingFragment2 : Fragment() {
    private lateinit var binding: FragmentOnboarding2Binding
    private lateinit var mContext: Context
    private lateinit var onboardActivity: OnboardingActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_2, container, false)
        onboardActivity = activity as OnboardingActivity

        initButton()


        return binding.root
    }

    private fun initButton() {
        binding.btnConfirm.setOnClickListener {
            //TODO: 라디오 버튼 게밈 정보 저장
            (activity as OnboardingActivity).replaceFragment(2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()
        joinLog(TAG, "onResume")
    }

    companion object {
        private const val TAG = "OnBoardingFragment2"
    }
}