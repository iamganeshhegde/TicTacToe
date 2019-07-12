package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Description_ {

    @SerializedName("urls")
    @Expose
    private var urls: List<Url___>? = null

    fun getUrls(): List<Url___>? {
        return urls
    }

    fun setUrls(urls: List<Url___>) {
        this.urls = urls
    }
}