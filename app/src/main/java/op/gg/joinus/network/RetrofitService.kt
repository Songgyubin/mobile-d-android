package op.gg.joinus.network



import op.gg.joinus.model.RoomCreate
import op.gg.joinus.model.RoomInfo
import retrofit2.Call
import retrofit2.http.*

import com.google.gson.JsonObject
import op.gg.joinus.model.OnboardInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query


interface RetrofitService {

    @GET("api/login/?")
    fun getLoginInfo(@Query("code") token: String): Call<JsonObject>

    @PUT("api/onboard")
    fun putOnboardInfo(@Body onboardInfo:OnboardInfo):Call<JsonObject>

    @GET("api/room/?")
    fun getRoom(): Call<List<RoomInfo>>

    @POST("api/room")
    fun postRoom(@Body roomCreate : RoomCreate):Call<Int>
}
