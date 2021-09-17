package op.gg.joinus.onboarding

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding2Binding
import op.gg.joinus.databinding.FragmentOnboarding3Binding
import op.gg.joinus.util.joinLog

class OnboardingFragment3 : Fragment() {
    private lateinit var binding: FragmentOnboarding3Binding
    private lateinit var onboardActivity: OnboardingActivity
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_3, container, false)
        onboardActivity = activity as OnboardingActivity
        initButton()
        initEditText()
        onboardActivity.setVisibleGameLogo(true)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun initButton() {
        binding.btnConfirm.setOnClickListener {
            onboardActivity.nickName = binding.edId.text.toString()
            (activity as OnboardingActivity).replaceFragment(3)
        }
    }

    private fun initEditText() {
        binding.edId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    binding.btnConfirm.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) = Unit

        })
    }

    override fun onResume() {
        super.onResume()
        joinLog(TAG, "onResume")
    }

    companion object {
        private const val TAG = "OnBoardingFragment3"
    }
}