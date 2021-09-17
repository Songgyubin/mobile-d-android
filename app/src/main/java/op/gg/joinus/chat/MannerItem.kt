package op.gg.joinus.chat

import op.gg.joinus.model.UserInfo

data class MannerItem(val userInfo: UserInfo) {
        lateinit var userName:String
        var check = true

        init{
            with(this){
                userName = userInfo.nickname
            }
        }
}