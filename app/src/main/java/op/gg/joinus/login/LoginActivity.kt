package op.gg.joinus.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import op.gg.joinus.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_JoinUs)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

}