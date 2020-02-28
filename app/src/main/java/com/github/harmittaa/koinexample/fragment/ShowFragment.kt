package com.github.harmittaa.koinexample.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.github.harmittaa.koinexample.R
import kotlinx.android.synthetic.main.fragment_show.*

/**
 * A simple [Fragment] subclass.
 */
class ShowFragment : Fragment() {
    lateinit var Name :String
    lateinit var Country:String
    lateinit var Temperature:String
    lateinit var humidity:String
    lateinit var pressure:String
    lateinit var wind:String
    lateinit var valueuv_index:String
    lateinit var lons :String
    lateinit var lats :String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveArguments()
        FindMap()
        textView_Name?.text = Name
        textView_Country?.text = Country
        textView_Temperature?.text = Temperature
        textView_valuewind?.text = wind
        textView_valuepressure?.text = pressure
        textView_valuehumidity?.text = humidity
        textView_valueuv_index?.text = valueuv_index



    }
    @SuppressLint("SetTextI18n")
    private fun retrieveArguments() {
        arguments?.let { arguments ->
            val args = ShowFragmentArgs.fromBundle(arguments)
            lons = args.Lon.toString()
            lats = args.Lat.toString()
            val icon = args.IconUrl
            Glide.with(this).load(icon).into(Weather_icons)
            Name = args.LocationName.toString()
            Country = args.CountryName.toString()
            Temperature = args.TemperatureValue.toString()+" °C"
            humidity= args.Humidity.toString() + " %"
            pressure = args.Pressure.toString()+ " mBar"
            wind  = args.Uvindex.toString()
            valueuv_index= args.Windspeed.toString()+"km/h NW"

        }
    }
    fun FindMap(){
        val find = ShowFragmentDirections.actionShowFragmentToMapFragment(
                Lon = lons,
                Lat =lats,
                CountryName = Name
        )
        textView_Name.setOnClickListener{ Showmap ->
            Showmap.findNavController().navigate(find)
        }
    }
}
