package op.gg.joinus.network


import op.gg.joinus.model.RoomCreate
import op.gg.joinus.model.RoomInfo
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("api/login/?")
    fun getLoginInfo(@Query("code") token: String): Call<Void>

    @GET("api/room/?")
    fun getRoom(): Call<List<RoomInfo>>

    @POST("api/room")
    fun postRoom(@Body roomCreate : RoomCreate):Call<Int>
}
