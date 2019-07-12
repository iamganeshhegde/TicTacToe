package com.example.tictactoe.rxjavaexample.networking

import com.example.tictactoe.rxjavaexample.model.WeatherCurrent
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("current.json?")
    fun getCurrentWeather(@Query("key") key:String, @Query("q") searchQuery:String):Observable<WeatherCurrent>


}