package io.andronicus.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.andronicus.forecastmvvm.data.network.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(private val apiService: ApixuWeatherApiService) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, language: String) {
        try {
            val response = apiService.getCurrentWeather(location,language).await()
            _downloadedCurrentWeather.postValue(response)
        } catch (e: Exception) {
        }
    }
}