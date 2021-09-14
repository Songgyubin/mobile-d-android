package op.gg.joinus.chat

import android.view.View
import op.gg.joinus.model.UserInfo

data class ChatRoomItem(
    var leader_pk: Int,
    var userInfo : UserInfo,
    var user_pk:Int
) {
    lateinit var imgPeople:String
    lateinit var name:String
    var vis = View.INVISIBLE
    init{
        with(this){
            if(userInfo.image_address != null && userInfo.image_address != ""){
                imgPeople = userInfo.image_address!!
            }
            else{
                imgPeople = ""
            }
            var text = userInfo.nickname
            if(user_pk == userInfo.pk){
                text+="(나)"
            }
            name = text
            if (leader_pk == userInfo.pk){
                vis = View.VISIBLE
            }
        }
    }
}