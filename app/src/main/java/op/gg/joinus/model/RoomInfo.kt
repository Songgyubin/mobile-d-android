package op.gg.joinus.model

import com.google.gson.annotations.SerializedName

data class RoomInfo (
    @SerializedName("game_name")
    var game_name: String = "",
    @SerializedName("highest_tier")
    var highest_tier: Int = 0,
    @SerializedName("is_start")
    var is_start: Int = 0,
    @SerializedName("leader_pk")
    var leader_pk: Int= 0,
    @SerializedName("lowest_tier")
    var lowest_tier: Int = 0,
    @SerializedName("now_people_cnt")
    var now_people_cnt:Int = 0,
    @SerializedName("people_number")
    var people_number: Int = 0,
    @SerializedName("pk")
    var pk: Int = 0,
    @SerializedName("room_name")
    var room_name: String = "",
    @SerializedName("start_date")
    var start_date: String ,
    @SerializedName("created_at")
    var created_at: String ,
    @SerializedName("voice_chat")
    var voice_chat: Boolean = true,
    @SerializedName("user_list")
    var user_list: List<UserInfo>
)