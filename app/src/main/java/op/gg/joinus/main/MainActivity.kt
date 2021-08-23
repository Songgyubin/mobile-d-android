package op.gg.joinus.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import op.gg.joinus.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}