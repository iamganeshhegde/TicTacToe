package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Url_____ {
    @SerializedName("urls")
    @Expose
    private var urls: List<Url______>? = null

    fun getUrls(): List<Url______>? {
        return urls
    }

    fun setUrls(urls: List<Url______>) {
        this.urls = urls
    }
}