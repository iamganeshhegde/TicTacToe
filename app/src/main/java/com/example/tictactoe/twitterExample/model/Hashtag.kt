package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Hashtag {

    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("indices")
    @Expose
    private var indices: List<Int>? = null

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getIndices(): List<Int>? {
        return indices
    }

    fun setIndices(indices: List<Int>) {
        this.indices = indices
    }
}