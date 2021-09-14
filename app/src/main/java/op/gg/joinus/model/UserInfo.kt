package op.gg.joinus.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("age")
    var age: Int = 0,
    @SerializedName("firebase_token")
    var firebaseToken: String = "string",
    @SerializedName("gender")
    var gender: Int = 0,
    @SerializedName("image_address")
    var image_address: String = "string",
    @SerializedName("nickname")
    var nickname: String = "string",
    @SerializedName("pk")
    var pk: Int = 0,
    @SerializedName("token")
    var token: String = "string",
    @SerializedName("login")
    var login: Boolean = false
)
