package op.gg.joinus.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide


fun joinLog(TAG: String, content: String) {
    Log.d(TAG, "[joinLog] $content")
}

fun getTier(game_name:String,tier:Int):String{
    var strTier:String = ""
    when(game_name) {
        "lol" -> {
            when (tier) {
                0 -> {
                    strTier = "아이언"
                }
                1 -> {
                    strTier = "브론즈"
                }
                2 -> {
                    strTier = "실버"
                }
                3 -> {
                    strTier = "골드"
                }
                4 -> {
                    strTier = "플래티넘"
                }
                5 -> {
                    strTier = "다이아"
                }
                6 -> {
                    strTier = "마스터"
                }
                7-> {
                    strTier = "챌린저"
                }
            }
        }
    }
    return strTier
}

fun setImg(context: Context, url:String, view:ImageView){
    Glide.with(context).load(url).into(view)
}