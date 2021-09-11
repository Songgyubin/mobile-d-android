package op.gg.joinus.chat

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import op.gg.joinus.R
import op.gg.joinus.databinding.ActivityChatBinding
import op.gg.joinus.main.MainActivity
import op.gg.joinus.model.RoomInfo

class ChatActivity : AppCompatActivity() {
    private lateinit var binding:ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.MainTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        binding.activityChat = this
        setToolbar()
    }
    fun initView(){

    }
    fun setToolbar(){
        val toolbar = binding.toolbarChat
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_navigation)
        toolbar.setNavigationOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            val bundle = Bundle()

            i.putExtras(bundle)
            startActivity(i)
        }
    }
}