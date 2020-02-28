package com.github.harmittaa.koinexample.model

import com.google.gson.annotations.SerializedName

data class Weather(
        @SerializedName("location")val location:LocationData,
        @SerializedName("current")val current:CurrentData

)

data class LocationData(
        val name:String,
        val country:String,
        val lat:String,
        val lon:String
)

data class CurrentData(
        val temperature:Int,
        val wind_speed:Int,
        val pressure:Int,
        val humidity:Int,
        val uv_index:Int,
        val weather_icons: List<String>
)