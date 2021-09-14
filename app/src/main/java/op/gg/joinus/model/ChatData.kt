package op.gg.joinus.model

data class ChatData(
    val user_name: String,
    val content: String,
    val room_pk: Int,
    val is_ban: Int,
    val target_name: String
)