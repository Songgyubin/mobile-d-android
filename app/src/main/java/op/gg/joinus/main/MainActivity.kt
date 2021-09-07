package op.gg.joinus.main

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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

    fun setToolbar(title:String,navigationButtonId:Int){
        binding.toolbarMainTitle.text = title
        val toolbar = binding.toolbarMain
        toolbar.setNavigationIcon(navigationButtonId)
        toolbar.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }
    fun setToolbar(menuId:Int, menuClickListener: Toolbar.OnMenuItemClickListener){
        val toolbar = binding.toolbarMain
        toolbar.inflateMenu(menuId)
        toolbar.setOnMenuItemClickListener(menuClickListener)
    }
    fun setToolbar(menuId:Int, menuClickListener: Toolbar.OnMenuItemClickListener, title:String, navigationButtonId:Int){
        val toolbar = binding.toolbarMain
        toolbar.setNavigationIcon(navigationButtonId)
        toolbar.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
        }
        toolbar.inflateMenu(menuId)
        toolbar.setOnMenuItemClickListener(menuClickListener)
        binding.toolbarMainTitle.text = title
        binding.toolbarMainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,17f)
    }

    fun resetToolbar(){
        val toolbar = binding.toolbarMain
        toolbar.navigationIcon = null
        toolbar.setNavigationOnClickListener {  }
        toolbar.menu.clear()
        binding.toolbarMainTitle.text = ""
    }

    fun setBottomNavigationView(){
        val bottomNavigationView = binding.bottomNavigationViewMain
        bottomNavigationView.visibility = View.GONE
    }

    fun returnBottomNavigationView(){
        val bottomNavigationView = binding.bottomNavigationViewMain
        bottomNavigationView.visibility = View.VISIBLE
    }

}