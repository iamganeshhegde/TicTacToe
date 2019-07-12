package com.example.tictactoe.twitterExample.networking

import android.content.Context
import android.net.ConnectivityManager
import com.example.tictactoe.rxjavaexample.networking.NetworkConnectionException
import com.example.tictactoe.twitterExample.model.Twitter
import io.reactivex.Observable

class TwitterRestApiImpl:TwitterRestApi {

    private lateinit var context: Context

    constructor(context: Context){
        this.context = context
    }

    var twitterApiClient = TwitterApiClient()


    fun isThereInternetConnection(): Boolean {
        val isConnected: Boolean

        val connectivityManager = this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo


        isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting

        return isConnected
    }

    override fun getTwitterData(): Observable<List<Twitter>> {

        if(isThereInternetConnection())
        {
            return twitterApiClient.connectToApi().getTwitterData()
        }else{
            return Observable.error(NetworkConnectionException())
        }

    }
}