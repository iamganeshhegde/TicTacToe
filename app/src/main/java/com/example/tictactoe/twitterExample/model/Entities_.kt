package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Entities_ {

    @SerializedName("url")
    @Expose
    private var url: Url_? = null
    @SerializedName("description")
    @Expose
    private var description: Description? = null

    fun getUrl(): Url_? {
        return url
    }

    fun setUrl(url: Url_) {
        this.url = url
    }

    fun getDescription(): Description? {
        return description
    }

    fun setDescription(description: Description) {
        this.description = description
    }

}