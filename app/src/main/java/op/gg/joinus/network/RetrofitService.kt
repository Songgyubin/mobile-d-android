package op.gg.joinus.network


import com.google.gson.JsonObject
import op.gg.joinus.model.OnboardInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface RetrofitService {

    @GET("api/login/?")
    fun getLoginInfo(@Query("code") token: String): Call<JsonObject>

    @PUT("api/onboard")
    fun putOnboardInfo(@Body onboardInfo:OnboardInfo):Call<JsonObject>

}
