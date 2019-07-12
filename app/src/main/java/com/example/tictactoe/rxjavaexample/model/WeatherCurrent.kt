package com.example.tictactoe.rxjavaexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class WeatherCurrent {
    @SerializedName("location")
    @Expose
    private var location: Location? = null
    @SerializedName("current")
    @Expose
    private var current: Current? = null

    fun getLocation(): Location? {
        return location
    }

    fun setLocation(location: Location) {
        this.location = location
    }

    fun getCurrent(): Current? {
        return current
    }

    fun setCurrent(current: Current) {
        this.current = current
    }


}