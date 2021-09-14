package op.gg.joinus.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.installations.FirebaseInstallations
import com.google.gson.JsonObject
import op.gg.joinus.R
import op.gg.joinus.databinding.FragmentOnboarding5Binding
import op.gg.joinus.main.MainActivity
import op.gg.joinus.model.OnboardInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.util.joinLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class OnboardingFragment5 : Fragment() {
    private lateinit var binding: FragmentOnboarding5Binding
    private lateinit var mContext: Context
    private lateinit var onboardActivity:OnboardingActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding_5, container, false)

        onboardActivity = activity as OnboardingActivity

        initButton()
        initImageButton()
        getFirebaseToken()

        return binding.root
    }

    private fun getFirebaseToken() {
        var firebaseToken: String? = null
        FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseToken = task.result.token
                firebaseToken?.let {

                    joinLog(TAG, it)
                }

            } else if (task.isCanceled) {
                joinLog(TAG, task.exception.toString())
            }
        }
    }

    private fun putOnboardInfo(onboardInfo: OnboardInfo) {
        val result: Call<JsonObject> =
            RetrofitClient.getInstance().buildRetrofit()
//                .putOnboardInfo(OnboardInfo(0, firebaseToken, 0, "", "", ""))
                .putOnboardInfo(OnboardInfo(21, "a", 0, "aa", "aaa", "123"))
        result.enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    joinLog(TAG, "put success")
                    joinLog(TAG, "onResponse!! ${response.body()}")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                joinLog(TAG, t.message!!)
            }
        })
    }

    private fun initButton() {

        onboardActivity.setOnboardInfo()

        binding.btnConfirm.setOnClickListener {
            putOnboardInfo(onboardActivity.getOnboardInfo())
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    private fun initImageButton() {
        binding.ibProfileImage.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY && resultCode == AppCompatActivity.RESULT_OK) {
            val uri = data?.data
            Glide.with(mContext).load(uri).into(binding.ibProfileImage)
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
        private const val TAG = "OnboardingFragment5"
        private const val GALLERY = 1001
    }
}