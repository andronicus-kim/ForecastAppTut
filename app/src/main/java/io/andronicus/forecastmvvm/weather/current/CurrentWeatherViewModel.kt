package io.andronicus.forecastmvvm.weather.current

import androidx.lifecycle.ViewModel;
import io.andronicus.forecastmvvm.data.repository.ForecastRepository
import io.andronicus.forecastmvvm.internal.UnitSystem
import io.andronicus.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private val unitSystem = UnitSystem.METRIC

    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
