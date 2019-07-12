package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Twitter {

    @SerializedName("statuses")
    @Expose
    private var statuses: List<Status>? = null
    @SerializedName("search_metadata")
    @Expose
    private var searchMetadata: SearchMetadata? = null

    fun getStatuses(): List<Status>? {
        return statuses
    }

    fun setStatuses(statuses: List<Status>) {
        this.statuses = statuses
    }

    fun getSearchMetadata(): SearchMetadata? {
        return searchMetadata
    }

    fun setSearchMetadata(searchMetadata: SearchMetadata) {
        this.searchMetadata = searchMetadata
    }
}