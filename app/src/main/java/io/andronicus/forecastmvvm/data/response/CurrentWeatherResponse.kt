package io.andronicus.forecastmvvm.data.response

import com.google.gson.annotations.SerializedName
import io.andronicus.forecastmvvm.data.db.entity.CurrentWeatherEntry
import io.andronicus.forecastmvvm.data.db.entity.Location

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)