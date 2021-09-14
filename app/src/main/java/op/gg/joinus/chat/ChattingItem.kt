package op.gg.joinus.chat

import android.view.View
import op.gg.joinus.model.UserInfo

// (sendType = false) == user, (sendType = true) == my
data class ChattingItem(
    val userInfo:UserInfo,
    val sendTime:String,
    val sendType:Boolean,
    val sendBody:String
) {
    lateinit var imgPeople:String
    lateinit var userName:String
    lateinit var time:String
    lateinit var body:String
    var userVisible = View.VISIBLE
    var myVisible = View.GONE

    init{
        with(this){
            if(userInfo.image_address != null && userInfo.image_address != ""){
                imgPeople = userInfo.image_address!!
            }
            else{
                imgPeople = ""
            }
            userName = userInfo.nickname
            time = sendTime
            body = sendBody
            if(sendType){
                userVisible =  View.GONE
                myVisible = View.VISIBLE
            }
            else{
                userVisible = View.VISIBLE
                myVisible = View.GONE
            }
        }
    }
}