package io.andronicus.forecastmvvm.weather.current

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.andronicus.forecastmvvm.R
import io.andronicus.forecastmvvm.data.network.ApixuWeatherApiService
import io.andronicus.forecastmvvm.data.network.ConnectivityInterceptorImpl
import io.andronicus.forecastmvvm.data.network.WeatherNetworkDataSourceImpl
import io.andronicus.forecastmvvm.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory : CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(CurrentWeatherViewModel::class.java)
        bindUI()
    }

    private fun bindUI(){
        launch {
        viewModel.weather.await().observe(this@CurrentWeatherFragment,
            Observer {
                if(it == null) return@Observer

                group_loading.visibility = View.GONE
                updateLocation("Nairobi")
                updateDateToToday()
            })
        }
    }

    private fun updateLocation(location : String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature : Double, feelsLike : Double){
        val unitAbbreviation = if(viewModel.isMetric) "°C" else "°F"
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition :String){
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume : Double){

    }
}
