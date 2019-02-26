package io.andronicus.forecastmvvm.data.provider

import io.andronicus.forecastmvvm.data.db.entity.WeatherLocation

/**
 * Created by Andronicus on 2/26/2019.
 */
interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}