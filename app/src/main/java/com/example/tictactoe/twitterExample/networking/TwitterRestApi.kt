package com.example.tictactoe.twitterExample.networking

import com.example.tictactoe.twitterExample.model.Twitter
import io.reactivex.Observable
import retrofit2.http.GET

interface TwitterRestApi {

    @GET()
    fun getTwitterData(): Observable<List<Twitter>>

}