package op.gg.joinus.onboarding

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.FirebaseApp
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityOnboardingBinding
import op.gg.joinus.model.OnboardInfo

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    // 0이면 남자 1이면 여자
    var gender = 0
    private var age = 0
    var gameName = "lol"
    var nickName = ""
    var gameTier = ""
    var imageAddress = ""
    var sub = ""
    private lateinit var onboardInfo: OnboardInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        initFragment()

    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction().run {
            this.add(R.id.fl_onboarding, OnboardingFragment1())
            this.commit()
        }
    }

    fun setOnboardInfo() {
        onboardInfo = OnboardInfo(age, "", gender, "", nickName, sub)
    }

    fun getOnboardInfo(): OnboardInfo = onboardInfo


    fun replaceFragment(fragmentIndex: Int) {
        supportFragmentManager.beginTransaction().run {
            when (fragmentIndex) {
                1 -> {
                    this.replace(R.id.fl_onboarding, OnboardingFragment2())
                        .addToBackStack("Onboarding2").commit()
                    binding.tvContent.text = "어떤 게임을\n즐기시나요?"
                }
                2 -> {
                    this.replace(R.id.fl_onboarding, OnboardingFragment3())
                        .addToBackStack("Onboarding3").commit()
                    binding.tvContent.text = "게임 아이디를\n알려주세요."
                }
                3 -> {
                    this.replace(R.id.fl_onboarding, OnboardingFragment4())
                        .addToBackStack("Onboarding4").commit()
                    binding.tvContent.text = "당신의 게임실력을\n알려주세요."
                }
                4 -> {
                    this.replace(R.id.fl_onboarding, OnboardingFragment5())
                        .addToBackStack("Onboarding5").commit()
                    binding.tvContent.text = "프로필 사진을\n설정해 주세요."
                }
                else -> {
                    Toast.makeText(this@OnboardingActivity, "매칭화면", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getAge(): Int = this.age

    fun setVisibleGameLogo(visible: Boolean){
        if (visible){
            binding.ivLogoSymbolLol.visibility = View.VISIBLE
        }else{
            binding.ivLogoSymbolLol.visibility = View.GONE
        }
    }


}