package op.gg.joinus.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding1Binding
import op.gg.joinus.util.joinLog

class OnboardingFragment1 : Fragment() {

    private lateinit var binding: FragmentOnboarding1Binding
    private lateinit var mContext: Context
    private lateinit var onboardActivity: OnboardingActivity

    private var gender = 0
    private var age = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_1, container, false)
        onboardActivity = activity as OnboardingActivity

        initButton()
        initNumberPicker()

        return binding.root
    }

    private fun initButton() {
        binding.btnConfirm.setOnClickListener {
            onboardActivity.age = age
            onboardActivity.gender = gender
            (activity as OnboardingActivity).replaceFragment(1)
        }
        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_man -> gender = 0
                R.id.rb_woman -> gender = 1
            }

        }


    }

    private fun initNumberPicker() {
        binding.npAge.apply {
            this.maxValue = 50
            this.minValue = 20
            this.value = 25
        }
    }


    override fun onResume() {
        super.onResume()
        joinLog(TAG, "onResume")
    }

    companion object {
        private const val TAG = "OnboardingFragment1"
    }
}