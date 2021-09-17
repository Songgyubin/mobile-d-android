package op.gg.joinus.network


import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.*

import com.google.gson.JsonObject
import op.gg.joinus.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query


interface RetrofitService {

    @GET("api/login/?")
    fun getLoginInfo(@Query("code") token: String): Call<UserInfo>

    @PUT("api/onboard")
    fun putOnboardInfo(@Body onboardInfo: OnboardInfo): Call<JsonObject>

    @GET("api/room/?")
    fun getRoom(): Call<List<RoomInfo>>

    @GET("api/room/{room_pk}")
    fun getRoomInfo(@Path("room_pk") room_pk:Int): Call<RoomInfo>

    @POST("api/room")
    fun postRoom(@Body roomCreate: RoomCreate): Call<Int>

    @FormUrlEncoded
    @POST("api/roomuser")
    fun postRoomUser(@FieldMap param:HashMap<String,Int>): Call<Int>

    @PUT("api/room")
    fun putRoom(@Body roomInfo: RoomUpdate): Call<Int>
    /**
     * param myroom
     *  0: 만든 방 조회
     *  1: 참여한 방 조회
     */
    @GET("api/room/user/{sub}/{myroom}")
    fun getMatchedRoom(@Path("sub") sub: String, @Path("myroom") num: Int): Call<List<RoomInfo>>

}
