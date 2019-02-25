package io.andronicus.forecastmvvm.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


const val CURRENT_WEATEHR = 0
@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
        @SerializedName("temp_c")
        val tempC: Double,
        @SerializedName("temp_f")
        val tempF: Double,
        @SerializedName("is_day")
        val isDay: Int,
        @Embedded(prefix = "condition_")
        val condition: Condition,
        @SerializedName("wind_mph")
        val windMph: Double,
        @SerializedName("wind_kph")
        val windKph: Double,
        @SerializedName("wind_dir")
        val windDir: String,
        @SerializedName("pressure_mb")
        val pressureMb: Double,
        @SerializedName("pressure_in")
        val pressureIn: Double,
        @SerializedName("feelslike_c")
        val feelslikeC: Double,
        @SerializedName("feelslike_f")
        val feelslikeF: Double,
        @SerializedName("vis_km")
        val visKm: Double,
        @SerializedName("vis_miles")
        val visMiles: Double,
        val uv: Double
    ){
        @PrimaryKey(autoGenerate = false)
        var id : Int = CURRENT_WEATEHR
}