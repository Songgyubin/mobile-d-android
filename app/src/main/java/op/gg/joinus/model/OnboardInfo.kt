package op.gg.joinus.model

import com.google.gson.annotations.SerializedName

data class OnboardInfo(
    @SerializedName("age")
    val age: Int = 0,
    @SerializedName("firebase_token")
    val firebaseToken: String = "",
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("image_address")
    val imageAddress: String = "",
    @SerializedName("nickname")
    val nickName: String = "",
    @SerializedName("sub")
    val sub: String = ""
)