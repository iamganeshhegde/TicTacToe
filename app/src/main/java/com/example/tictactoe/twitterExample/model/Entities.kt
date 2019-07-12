package com.example.tictactoe.twitterExample.model

import retrofit2.http.Url
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Entities {
    @SerializedName("hashtags")
    @Expose
    private var hashtags: List<Hashtag>? = null
    @SerializedName("symbols")
    @Expose
    private var symbols: List<Any>? = null
    @SerializedName("user_mentions")
    @Expose
    private var userMentions: List<Any>? = null
    @SerializedName("urls")
    @Expose
    private var urls: List<Url>? = null

    fun getHashtags(): List<Hashtag>? {
        return hashtags
    }

    fun setHashtags(hashtags: List<Hashtag>) {
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

    fun getUrls(): List<Url>? {
        return urls
    }

    fun setUrls(urls: List<Url>) {
        this.urls = urls
    }

}