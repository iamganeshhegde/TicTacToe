package com.example.tictactoe.twitterExample.presenter

import com.example.tictactoe.twitterExample.model.Twitter
import com.example.tictactoe.twitterExample.model.User
import com.example.tictactoe.twitterExample.networking.TwitterRestApi
import com.example.tictactoe.twitterExample.networking.TwitterRestApiImpl
import com.example.tictactoe.twitterExample.ui.activities.TwitterMainActivity
import com.example.tictactoe.twitterExample.ui.screen_contracts.ITwitterMainListener



class TwitterMainPresenter() {

    lateinit var twitterList: ArrayList<Twitter>

    lateinit var iTwitterMainListener:ITwitterMainListener

    fun setTwitterMainPresenter(iTwitterMainListener: ITwitterMainListener){
        this.iTwitterMainListener = iTwitterMainListener
    }

    fun getTwitterData() {
        var twitterRestApiImpl = TwitterRestApiImpl(iTwitterMainListener as TwitterMainActivity)

//        val twitter =




    }


}