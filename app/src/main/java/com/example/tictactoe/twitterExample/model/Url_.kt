package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Url_ {

    @SerializedName("urls")
    @Expose
    private var urls: List<Url__>? = null

    fun getUrls(): List<Url__>? {
        return urls
    }

    fun setUrls(urls: List<Url__>) {
        this.urls = urls
    }
}