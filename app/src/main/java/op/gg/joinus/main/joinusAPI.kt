package op.gg.joinus.main

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface joinus {
    @GET("/api/room")
    fun getRoom(): Call<List<Room>>
}