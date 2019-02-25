package io.andronicus.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import io.andronicus.forecastmvvm.data.network.response.CurrentWeatherResponse

/**
 * Created by Andronicus on 2/25/2019.
 */
interface WeatherNetworkDataSource {

    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String, language : String)
}