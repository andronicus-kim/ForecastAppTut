package io.andronicus.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.andronicus.forecastmvvm.data.db.CurrentWeatherDao
import io.andronicus.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import io.andronicus.forecastmvvm.data.network.WeatherNetworkDataSource
import io.andronicus.forecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastRepositoryImpl(
    private val currentWeatherDao : CurrentWeatherDao,
    private val weatherNetworkDataSource : WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever(Observer {
            //persist to local storage
            persistFetchedCurrentWeather(it)
        })
    }
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather : CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }
}