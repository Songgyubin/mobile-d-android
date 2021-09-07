package op.gg.joinus.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentHome: HomeFragment = HomeFragment()
    private val fragmentMyMatch: MyMatchFragment = MyMatchFragment()
    private val fragmentMy: MyFragment = MyFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activityMain = this
        binding.bottomNavigationViewMain.setOnItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.homeItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainerViewMain.id, fragmentHome).commit()
                }
                R.id.myMatchItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainerViewMain.id, fragmentMyMatch).commit()
                }
                R.id.myItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.fragmentContainerViewMain.id, fragmentMy).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}