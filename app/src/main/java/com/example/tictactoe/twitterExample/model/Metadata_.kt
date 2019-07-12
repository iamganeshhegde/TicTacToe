package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Metadata_ {

    @SerializedName("iso_language_code")
    @Expose
    private var isoLanguageCode: String? = null
    @SerializedName("result_type")
    @Expose
    private var resultType: String? = null

    fun getIsoLanguageCode(): String? {
        return isoLanguageCode
    }

    fun setIsoLanguageCode(isoLanguageCode: String) {
        this.isoLanguageCode = isoLanguageCode
    }

    fun getResultType(): String? {
        return resultType
    }

    fun setResultType(resultType: String) {
        this.resultType = resultType
    }
}