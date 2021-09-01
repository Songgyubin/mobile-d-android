package op.gg.joinus.model

data class RoomCreate(
    var game_name: String,
    var highest_tier: Int,
    var lowest_tier: Int,
    var people_number: Int,
    val room_name: String,
    var start_date: RoomStartDate,
    var user_pk: Int,
    var voice_chat: Boolean

    )