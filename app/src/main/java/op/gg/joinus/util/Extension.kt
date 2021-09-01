package op.gg.joinus.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


fun joinLog(TAG: String, content: String) {
    Log.d(TAG, "[joinLog] $content")
}

fun getTier(game_name:String,tier:Int):String{
    var strTier = ""
    when(game_name) {
        "league of legends" -> {
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
        else ->{}
    }
    return strTier
}

@SuppressLint("SimpleDateFormat")
//date1 - date2
fun diffCalendar(date1:String, date2:String):String{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dateFormat1 = if (date1 != ""){
        dateFormat.parse(date1)!!.time
    }
    else{
        Calendar.getInstance().timeInMillis
    }
    val dateFormat2 = if (date2 != ""){
        dateFormat.parse(date2)!!.time
    }
    else{
        Calendar.getInstance().timeInMillis
    }
    val dateDiff = (dateFormat1 - dateFormat2)
    val testdata = Calendar.getInstance()
    testdata.timeInMillis = dateFormat2
    return if (dateDiff/(60 * 1000) <= 0){
        ""
    }
    else if ((dateDiff/(24 * 60 * 60 * 1000)) != 0.toLong()){
        "${dateDiff/(24 * 60 * 60 * 1000)}일"
    }
    else if((dateDiff/(60 * 60 * 1000)) != 0.toLong()){
        "${dateDiff/(60 * 60 * 1000)}시간"
    }
    else {
        "${dateDiff/(60 * 1000)}분"
    }
}

fun setImg(context: Context, url:String, view:ImageView){
    Glide.with(context).load(url).into(view)
}