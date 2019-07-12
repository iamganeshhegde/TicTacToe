package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Entities__ {

    @SerializedName("hashtags")
    @Expose
    private var hashtags: List<Any>? = null
    @SerializedName("symbols")
    @Expose
    private var symbols: List<Any>? = null
    @SerializedName("user_mentions")
    @Expose
    private var userMentions: List<Any>? = null
    @SerializedName("urls")
    @Expose
    private var urls: List<Url____>? = null

    fun getHashtags(): List<Any>? {
        return hashtags
    }

    fun setHashtags(hashtags: List<Any>) {
        this.hashtags = hashtags
    }

    fun getSymbols(): List<Any>? {
        return symbols
    }

    fun setSymbols(symbols: List<Any>) {
        this.symbols = symbols
    }

    fun getUserMentions(): List<Any>? {
        return userMentions
    }

    fun setUserMentions(userMentions: List<Any>) {
        this.userMentions = userMentions
    }

    fun getUrls(): List<Url____>? {
        return urls
    }

    fun setUrls(urls: List<Url____>) {
        this.urls = urls
    }
}