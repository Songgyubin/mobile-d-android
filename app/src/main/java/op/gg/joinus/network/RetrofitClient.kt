package op.gg.joinus.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private val retrofitClient: RetrofitClient = RetrofitClient()
        fun getInstance(): RetrofitClient = retrofitClient
        val url = "http://ec2-3-128-67-103.us-east-2.compute.amazonaws.com:80/"
//        val url = "http://ec2-3-128-67-103.us-east-2.compute.amazonaws.com:8080/"
    }

    fun buildRetrofit(): RetrofitService {
        val gson = GsonBuilder().setLenient().create()
        var retrofit: Retrofit? = null
        retrofit =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit!!.create(RetrofitService::class.java)
    }
}
