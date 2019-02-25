package io.andronicus.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.andronicus.forecastmvvm.data.db.entity.CURRENT_WEATEHR
import io.andronicus.forecastmvvm.data.db.entity.CurrentWeatherEntry
import io.andronicus.forecastmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry
import io.andronicus.forecastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry

/**
 * Created by Andronicus on 2/25/2019.
 */

@Dao
interface CurrentWeatherDao {

    //update or insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATEHR")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CURRENT_WEATEHR")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>
}