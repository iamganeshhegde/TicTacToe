package com.example.tictactoe.rxjavaexample.networking

import android.content.Context
import android.net.ConnectivityManager
import com.example.tictactoe.rxjavaexample.model.WeatherCurrent
import com.example.tictactoe.rxjavaexample.utils.Constants
import io.reactivex.Observable
import okhttp3.internal.Util

class RestApiImpl(context: Context) :RestApi {

    lateinit var context:Context

    init {
        this.context = context

    }

    var apiClient = ApiClient()

    fun isThereInternetConnection(): Boolean {
        val isConnected: Boolean

        val connectivityManager = this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo


        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting

        return isConnected
    }

    override fun getCurrentWeather(key: String, searchQuery: String): Observable<WeatherCurrent> {


        if(isThereInternetConnection())
        {
            return apiClient.connectToApi().getCurrentWeather(key,searchQuery)
        }else
        {
            return Observable.error(NetworkConnectionException())
        }

    }
}