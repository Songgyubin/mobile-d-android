package op.gg.joinus.main

import com.google.gson.annotations.SerializedName

data class Room(
    @SerializedName("game_name")
    var game_name: String = "",
    @SerializedName("highest_tier")
    var highest_tier: Int = 0,
    @SerializedName("is_start")
    var is_start: Int = 0,
    @SerializedName("lowest_tier")
    var lowest_tier: Int = 0,
    @SerializedName("people_number")
    var people_number: Int = 0,
    @SerializedName("pk")
    var pk: Int = 0,
    @SerializedName("room_name")
    var room_name: String = "",
    @SerializedName("start_date")
    var start_date:Start_date = Start_date(),
    @SerializedName("voice_chat")
    var voice_chat: Boolean = true
)

data class Start_date(
    @SerializedName("date")
    var date: Int = 0,
    @SerializedName("day")
    var day: Int = 0,
    @SerializedName("hours")
    var hours: Int = 0,
    @SerializedName("minutes")
    var minutes: Int = 0,
    @SerializedName("month")
    var month: Int = 0,
    @SerializedName("nanos")
    var nanos: Int = 0,
    @SerializedName("seconds")
    var seconds: Int = 0,
    @SerializedName("time")
    var time: Int = 0,
    @SerializedName("timezoneOffset")
    var timezoneOffset: Int = 0,
    @SerializedName("year")
    var year: Int = 0,
)