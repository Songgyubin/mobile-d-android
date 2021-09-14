package op.gg.joinus.model

import com.google.gson.annotations.SerializedName

data class FriendsInfo(
    @SerializedName("end_id")
    var endId: Int = 0,
    // true: 친구, false: 블랙리스트
    @SerializedName("friend_or_black")
    var friendOrBlack: Int = 0,
    @SerializedName("pk")
    var pk: Int = 0,
    @SerializedName("start_id")
    var startId: Int = 0
)