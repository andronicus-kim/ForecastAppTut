package io.andronicus.forecastmvvm.data.db.unitlocalized

/**
 * Created by Andronicus on 2/25/2019.
 */
interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}