package io.andronicus.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.andronicus.forecastmvvm.data.db.CurrentWeatherDao
import io.andronicus.forecastmvvm.data.db.WeatherLocationDao
import io.andronicus.forecastmvvm.data.db.entity.WeatherLocation
import io.andronicus.forecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import io.andronicus.forecastmvvm.data.network.WeatherNetworkDataSource
import io.andronicus.forecastmvvm.data.network.response.CurrentWeatherResponse
import io.andronicus.forecastmvvm.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao : CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource : WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever(Observer {
            //persist to local storage
            persistFetchedCurrentWeather(it)
        })
    }
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            iniWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO){
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather : CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    private suspend fun iniWeatherData(){
        val lastWeatherLocation = weatherLocationDao.getLocation().value

        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)){
            fetchCurrentWeather()
            return
        }

        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(locationProvider.getPreferredLocationString(),Locale.getDefault().language)
    }

    private fun isFetchCurrentNeeded(lastFetchTime : ZonedDateTime) : Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}