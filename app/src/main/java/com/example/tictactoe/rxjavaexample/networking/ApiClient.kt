package com.example.tictactoe.rxjavaexample.networking

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {


    var BASE_URL = "https://api.apixu.com/v1/"
    val READ_TIMEOUT: Long = 1
    val CONNECT_TIMEOUT: Long = 1


    fun connectToApi():RestApi
    {

        var retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient())
            .build()

        return retrofit.create(RestApi::class.java)
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