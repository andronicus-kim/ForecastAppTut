package io.andronicus.forecastmvvm.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import io.andronicus.forecastmvvm.R
import io.andronicus.forecastmvvm.data.network.ApixuWeatherApiService
import io.andronicus.forecastmvvm.data.network.ConnectivityInterceptorImpl
import io.andronicus.forecastmvvm.data.network.WeatherNetworkDataSource
import io.andronicus.forecastmvvm.data.network.WeatherNetworkDataSourceImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
        val remoteDataSource = WeatherNetworkDataSourceImpl(apiService)

        remoteDataSource.downloadedCurrentWeather.observe(this, Observer {
            text_view.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            remoteDataSource.fetchCurrentWeather("Nairobi","en")
        }
    }

}
