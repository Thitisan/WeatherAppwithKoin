package com.github.harmittaa.koinexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.harmittaa.koinexample.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment(), OnMapReadyCallback{

    lateinit var lon :String
    lateinit var lat :String
    lateinit var Name :String

    private lateinit var mMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        retrieveMap()
        if (googleMap != null) {
            mMap = googleMap
        val lon = lon.toDouble()
        val lat = lat.toDouble()
            val locationName = Name.toString()
        // Add a marker in Sydney and move the camera
        val location = LatLng(lat, lon)
        mMap.addMarker(MarkerOptions().position(location).title("$locationName"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    private fun retrieveMap() {
        arguments?.let { arguments ->
            val args = MapFragmentArgs.fromBundle(arguments)
            lon = args.Lon.toString()
            lat = args.Lat.toString()
            Name = args.CountryName.toString()
        }
    }


}
