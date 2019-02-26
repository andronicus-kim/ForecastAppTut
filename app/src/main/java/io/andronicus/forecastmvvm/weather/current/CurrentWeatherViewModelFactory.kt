package io.andronicus.forecastmvvm.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.andronicus.forecastmvvm.data.provider.UnitProvider
import io.andronicus.forecastmvvm.data.repository.ForecastRepository

/**
 * Created by Andronicus on 2/26/2019.
 */
class CurrentWeatherViewModelFactory(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository,unitProvider) as T
    }
}