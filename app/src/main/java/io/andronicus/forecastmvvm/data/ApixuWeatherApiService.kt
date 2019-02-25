package io.andronicus.forecastmvvm.data

import retrofit2.http.GET

/**
 * Created by Andronicus on 2/25/2019.
 */

const val API_KEY = "829f083b43ad43f59f193522192502"
//HTTP: http://api.apixu.com/v1/current.json?key=829f083b43ad43f59f193522192502&q=Paris

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather()
}