package io.andronicus.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.andronicus.forecastmvvm.data.db.CurrentWeatherDao
import io.andronicus.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import io.andronicus.forecastmvvm.data.network.WeatherNetworkDataSource

class ForecastRepositoryImpl(
    private val currentWeatherDao : CurrentWeatherDao,
    private val weatherNetworkDataSource : WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever(Observer {
            //persist to local storage
        })
    }
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<UnitSpecificCurrentWeatherEntry> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}