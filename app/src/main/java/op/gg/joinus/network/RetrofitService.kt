package op.gg.joinus.network


import com.google.gson.JsonObject
import op.gg.joinus.model.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("login/?")
    fun getLoginInfo(@Query("code") token: String): Call<Void>

}