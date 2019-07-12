package com.example.tictactoe.twitterExample.networking

import com.example.tictactoe.rxjavaexample.networking.RestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TwitterApiClient {

    var BASE_URL = ""
    val READ_TIMEOUT: Long = 1
    val CONNECT_TIMEOUT: Long = 1

    fun connectToApi(): TwitterRestApi
    {

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient())
            .build()

        return retrofit.create(TwitterRestApi::class.java)
    }

    fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(logging)
        builder.readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        return builder.build()
    }
}