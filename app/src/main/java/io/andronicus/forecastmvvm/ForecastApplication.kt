package io.andronicus.forecastmvvm

import android.app.Application
import android.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import io.andronicus.forecastmvvm.data.db.ForecastDatabase
import io.andronicus.forecastmvvm.data.network.*
import io.andronicus.forecastmvvm.data.provider.UnitProvider
import io.andronicus.forecastmvvm.data.provider.UnitProviderImpl
import io.andronicus.forecastmvvm.data.repository.ForecastRepository
import io.andronicus.forecastmvvm.data.repository.ForecastRepositoryImpl
import io.andronicus.forecastmvvm.weather.current.CurrentWeatherViewModel
import io.andronicus.forecastmvvm.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Andronicus on 2/25/2019.
 */
class ForecastApplication : Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(),instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this@ForecastApplication)
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false)
    }
}