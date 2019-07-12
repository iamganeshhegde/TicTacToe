package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Url {
    @SerializedName("url")
    @Expose
    private var url: String? = null
    @SerializedName("expanded_url")
    @Expose
    private var expandedUrl: String? = null
    @SerializedName("display_url")
    @Expose
    private var displayUrl: String? = null
    @SerializedName("indices")
    @Expose
    private var indices: List<Int>? = null

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getExpandedUrl(): String? {
        return expandedUrl
    }

    fun setExpandedUrl(expandedUrl: String) {
        this.expandedUrl = expandedUrl
    }

    fun getDisplayUrl(): String? {
        return displayUrl
    }

    fun setDisplayUrl(displayUrl: String) {
        this.displayUrl = displayUrl
    }

    fun getIndices(): List<Int>? {
        return indices
    }

    fun setIndices(indices: List<Int>) {
        this.indices = indices
    }
}