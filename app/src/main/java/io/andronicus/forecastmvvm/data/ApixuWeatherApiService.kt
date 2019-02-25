package io.andronicus.forecastmvvm.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.andronicus.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Andronicus on 2/25/2019.
 */

const val API_KEY = "829f083b43ad43f59f193522192502"
//HTTP: http://api.apixu.com/v1/current.json?key=829f083b43ad43f59f193522192502&q=Paris

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(@Query("q") location : String,
                          @Query("lang") language : String = "en") : Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke() : ApixuWeatherApiService{
            val requestInterceptor = Interceptor{
                chain -> val url = chain.request().url()
                .newBuilder()
                .addQueryParameter("key", API_KEY)
                .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(" http://api.apixu.com/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}