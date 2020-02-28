package com.github.harmittaa.koinexample.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.github.harmittaa.koinexample.R
import com.github.harmittaa.koinexample.databinding.FragmentViewBinding
import com.github.harmittaa.koinexample.model.CurrentData
import com.github.harmittaa.koinexample.model.LocationData
import com.github.harmittaa.koinexample.model.Weather
import com.github.harmittaa.koinexample.networking.Resource
import com.github.harmittaa.koinexample.networking.Status
import kotlinx.android.synthetic.main.fragment_view.*
import org.json.JSONArray
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { WeatherFragment() }
}

class WeatherFragment : Fragment() {
    private val exampleViewModel: WeatherViewModel by viewModel()
    private lateinit var binding: FragmentViewBinding
    lateinit var json:JSONArray


    private val observer = Observer<Resource<Weather>> {
        when (it.status) {
            Status.SUCCESS -> updateTemperatureText(it.data!!.location,it.data.current)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> showLoading()
        }
    }

    private var action =
            WeatherFragmentDirections.actionWeatherFragment2ToShowFragment(
                    LocationName = "London",
                    CountryName = "England",
                    TemperatureValue = 18,
                    Uvindex = 0,
                    Windspeed = 0,
                    Pressure = 0,
                    Humidity = 0,
                    Lat = "0.0",
                    Lon = "0.0"

//                    Weathericons = "Wwww

)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view, container, false)
        binding.viewModel = exampleViewModel
        exampleViewModel.weather.observe(viewLifecycleOwner, observer)

        return binding.root
    }



    @SuppressLint("SetTextI18n")
    private fun showLoading() {
        binding.weatherInfo.text = "Loading..."
    }

    @SuppressLint("SetTextI18n")
    private fun showError(message: String) {
        binding.weatherInfo.text = "Error: $message"
    }

    @SuppressLint("SetTextI18n")
    private fun updateTemperatureText(location: LocationData, currentData: CurrentData) {
        binding.weatherInfo.text = "Temperature at ${location.name} is ${currentData.temperature} celsius"
        locationEt.visibility = View.GONE
        Show_button.visibility = View.VISIBLE
        action =
                WeatherFragmentDirections.actionWeatherFragment2ToShowFragment(
                        LocationName = location.name,
                        CountryName = location.country,
                        TemperatureValue = currentData.temperature,
//                     Weathericons = currentData.weatherIcons[0],
                        Uvindex = currentData.uv_index,
                        Windspeed = currentData.wind_speed,
                        Pressure = currentData.pressure,
                        Humidity = currentData.humidity,
                        Lon = location.lon,
                        Lat = location.lat

                )
        Show_button.setOnClickListener{Showvalue ->
            Showvalue.findNavController().navigate(action)
        }
    }

}