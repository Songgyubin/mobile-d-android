package op.gg.joinus.model

import com.google.gson.annotations.SerializedName

data class RoomStartDate (
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