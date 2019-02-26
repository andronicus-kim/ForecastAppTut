package io.andronicus.forecastmvvm.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.andronicus.forecastmvvm.data.repository.ForecastRepository

/**
 * Created by Andronicus on 2/26/2019.
 */
class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository) as T
    }
}