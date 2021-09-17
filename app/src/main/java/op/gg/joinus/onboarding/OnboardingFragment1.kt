package op.gg.joinus.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding1Binding
import op.gg.joinus.dialog.AgeInterface
import op.gg.joinus.dialog.CustomNumberPickerDialog
import op.gg.joinus.util.joinLog

class OnboardingFragment1 : Fragment(), AgeInterface {

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

        return binding.root
    }

    private fun initButton() {
        binding.btnConfirm.setOnClickListener {
            onboardActivity.gender = gender
            (activity as OnboardingActivity).replaceFragment(1)
        }
        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_man -> gender = 0
                R.id.rb_woman -> gender = 1
            }
            binding.btnAgeChoice.isEnabled = true
        }
        binding.btnAgeChoice.setOnClickListener {
            CustomNumberPickerDialog(
                mContext,
                R.layout.dialog_age_choice,
                this
            ).show()
        }

    }

    override fun onResume() {
        super.onResume()
        joinLog(TAG, "onResume")
    }

    companion object {
        private const val TAG = "OnboardingFragment1"
    }

    override fun setAge(age: Int) {
        onboardActivity.setAge(age)
        binding.btnAgeChoice.text = "${age}세"
        binding.btnConfirm.isEnabled = true
    }
}