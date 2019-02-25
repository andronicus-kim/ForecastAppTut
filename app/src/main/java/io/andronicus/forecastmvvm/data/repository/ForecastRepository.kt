package io.andronicus.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import io.andronicus.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

/**
 * Created by Andronicus on 2/25/2019.
 */
interface ForecastRepository {

    suspend fun getCurrentWeather(metric : Boolean) : LiveData<UnitSpecificCurrentWeatherEntry>
}