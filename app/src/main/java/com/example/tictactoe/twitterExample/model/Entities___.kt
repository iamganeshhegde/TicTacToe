package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Entities___ {

    @SerializedName("url")
    @Expose
    private var url: Url_____? = null
    @SerializedName("description")
    @Expose
    private var description: Description_? = null

    fun getUrl(): Url_____? {
        return url
    }

    fun setUrl(url: Url_____) {
        this.url = url
    }

    fun getDescription(): Description_? {
        return description
    }

    fun setDescription(description: Description_) {
        this.description = description
    }
}