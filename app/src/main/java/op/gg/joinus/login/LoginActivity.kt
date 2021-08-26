package op.gg.joinus.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import op.gg.joinus.R

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_JoinUs)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initLogin()
    }

    private fun initLogin() {
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail() // email addresses도 요청함
            .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

}