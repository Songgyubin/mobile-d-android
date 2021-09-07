package op.gg.joinus.model

data class UserInfo(
    val age: Int,
    val firebase_token: String?,
    val gender: Int,
    val image_address: String?,
    val nickname: String,
    val pk: Int,
    val token: String,
    val login:Boolean
)
