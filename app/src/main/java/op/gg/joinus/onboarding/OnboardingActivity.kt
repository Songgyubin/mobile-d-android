package op.gg.joinus.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)

        initFragment()
        initButton()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction().run {
            this.add(R.id.fl_onboarding, OnboardingFragment1())
            this.commit()
        }
    }

    private fun initButton() {
        binding.btnConfirm.setOnClickListener {

        }
    }

}