package io.andronicus.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import io.andronicus.forecastmvvm.data.db.entity.CurrentWeatherEntry
import io.andronicus.forecastmvvm.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)