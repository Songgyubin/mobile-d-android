package op.gg.joinus.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.gson.JsonObject
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityLoginBinding
import op.gg.joinus.model.UserInfo
import op.gg.joinus.network.RetrofitClient
import op.gg.joinus.onboarding.OnboardingActivity
import op.gg.joinus.util.SharedPreferenceManager
import op.gg.joinus.util.joinLog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(500)
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initLogin()
        initButton()
    }

    private fun initLogin() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail() // email addresses도 요청함
            .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);
    }

    private fun initButton() {

//        binding.btnGoogleLogin.setOnClickListener { getToken() }
        binding.btnGoogleLogin.setOnClickListener {
            //            signIn("4%2F0AX4XfWgPRNBXe6_Q09FzP28x3dySDblIYVKcGbOfVjJc1sSF8SYCw34QX5xyA2vhB3leXA&scope=profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile")
            // 임시값 ( sub == token )
            SharedPreferenceManager.let {
                it.setInt(this, "age", 21)
                it.setString(this, "firebaseToken", "a")
                it.setInt(this, "gender", 0)
                it.setString(this, "imageAddress", "aa")
                it.setBoolean(this, "login", true)
                it.setString(this, "nickName", "aaa")
                it.setInt(this, "pk", 0)
                it.setString(this, "sub", "123")
            }

            startActivity(Intent(this@LoginActivity, OnboardingActivity::class.java))
        }

    }

    private fun getToken() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    private fun signIn(token: String) {
        val result: Call<UserInfo> =
            RetrofitClient.getInstance().buildRetrofit().getLoginInfo(token)
        result.enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val body = response.body()
                joinLog(TAG, "success: ${body.toString()}")
                // TODO: 로그인 완료되면 데이터들 저장하기
                // token = sub

                SharedPreferenceManager.setString(this@LoginActivity,"sub",body!!.token)

                startActivity(Intent(this@LoginActivity, OnboardingActivity::class.java))
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                joinLog(TAG, "error: ${t.message}")
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val acct: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            joinLog(TAG, "token: ${acct.idToken}")
            signIn(acct.idToken)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            joinLog(TAG, "signInResult:failed code=${e.statusCode}")
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
        private const val RC_SIGN_IN = 1001
    }
}