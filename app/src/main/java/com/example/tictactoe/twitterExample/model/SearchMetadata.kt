package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class SearchMetadata {

    @SerializedName("completed_in")
    @Expose
    private var completedIn: Double? = null
    @SerializedName("max_id")
    @Expose
    private var maxId: Int? = null
    @SerializedName("max_id_str")
    @Expose
    private var maxIdStr: String? = null
    @SerializedName("next_results")
    @Expose
    private var nextResults: String? = null
    @SerializedName("query")
    @Expose
    private var query: String? = null
    @SerializedName("refresh_url")
    @Expose
    private var refreshUrl: String? = null
    @SerializedName("count")
    @Expose
    private var count: Int? = null
    @SerializedName("since_id")
    @Expose
    private var sinceId: Int? = null
    @SerializedName("since_id_str")
    @Expose
    private var sinceIdStr: String? = null

    fun getCompletedIn(): Double? {
        return completedIn
    }

    fun setCompletedIn(completedIn: Double?) {
        this.completedIn = completedIn
    }

    fun getMaxId(): Int? {
        return maxId
    }

    fun setMaxId(maxId: Int?) {
        this.maxId = maxId
    }

    fun getMaxIdStr(): String? {
        return maxIdStr
    }

    fun setMaxIdStr(maxIdStr: String) {
        this.maxIdStr = maxIdStr
    }

    fun getNextResults(): String? {
        return nextResults
    }

    fun setNextResults(nextResults: String) {
        this.nextResults = nextResults
    }

    fun getQuery(): String? {
        return query
    }

    fun setQuery(query: String) {
        this.query = query
    }

    fun getRefreshUrl(): String? {
        return refreshUrl
    }

    fun setRefreshUrl(refreshUrl: String) {
        this.refreshUrl = refreshUrl
    }

    fun getCount(): Int? {
        return count
    }

    fun setCount(count: Int?) {
        this.count = count
    }

    fun getSinceId(): Int? {
        return sinceId
    }

    fun setSinceId(sinceId: Int?) {
        this.sinceId = sinceId
    }

    fun getSinceIdStr(): String? {
        return sinceIdStr
    }

    fun setSinceIdStr(sinceIdStr: String) {
        this.sinceIdStr = sinceIdStr
    }
}