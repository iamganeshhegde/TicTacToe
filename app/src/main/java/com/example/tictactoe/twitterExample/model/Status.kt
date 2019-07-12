package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Status {

    @Expose
    private var createdAt: String? = null
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("id_str")
    @Expose
    private var idStr: String? = null
    @SerializedName("text")
    @Expose
    private var text: String? = null
    @SerializedName("truncated")
    @Expose
    private var truncated: Boolean? = null
    @SerializedName("entities")
    @Expose
    private var entities: Entities? = null
    @SerializedName("metadata")
    @Expose
    private var metadata: Metadata? = null
    @SerializedName("source")
    @Expose
    private var source: String? = null
    @SerializedName("in_reply_to_status_id")
    @Expose
    private var inReplyToStatusId: Any? = null
    @SerializedName("in_reply_to_status_id_str")
    @Expose
    private var inReplyToStatusIdStr: Any? = null
    @SerializedName("in_reply_to_user_id")
    @Expose
    private var inReplyToUserId: Any? = null
    @SerializedName("in_reply_to_user_id_str")
    @Expose
    private var inReplyToUserIdStr: Any? = null
    @SerializedName("in_reply_to_screen_name")
    @Expose
    private var inReplyToScreenName: Any? = null
    @SerializedName("user")
    @Expose
    private var user: User? = null
    @SerializedName("geo")
    @Expose
    private var geo: Any? = null
    @SerializedName("coordinates")
    @Expose
    private var coordinates: Any? = null
    @SerializedName("place")
    @Expose
    private var place: Any? = null
    @SerializedName("contributors")
    @Expose
    private var contributors: Any? = null
    @SerializedName("is_quote_status")
    @Expose
    private var isQuoteStatus: Boolean? = null
    @SerializedName("quoted_status_id")
    @Expose
    private var quotedStatusId: Int? = null
    @SerializedName("quoted_status_id_str")
    @Expose
    private var quotedStatusIdStr: String? = null
    @SerializedName("quoted_status")
    @Expose
    private var quotedStatus: QuotedStatus? = null
    @SerializedName("retweet_count")
    @Expose
    private var retweetCount: Int? = null
    @SerializedName("favorite_count")
    @Expose
    private var favoriteCount: Int? = null
    @SerializedName("favorited")
    @Expose
    private var favorited: Boolean? = null
    @SerializedName("retweeted")
    @Expose
    private var retweeted: Boolean? = null
    @SerializedName("possibly_sensitive")
    @Expose
    private var possiblySensitive: Boolean? = null
    @SerializedName("lang")
    @Expose
    private var lang: String? = null

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getIdStr(): String? {
        return idStr
    }

    fun setIdStr(idStr: String) {
        this.idStr = idStr
    }

    fun getText(): String? {
        return text
    }

    fun setText(text: String) {
        this.text = text
    }

    fun getTruncated(): Boolean? {
        return truncated
    }

    fun setTruncated(truncated: Boolean?) {
        this.truncated = truncated
    }

    fun getEntities(): Entities? {
        return entities
    }

    fun setEntities(entities: Entities) {
        this.entities = entities
    }

    fun getMetadata(): Metadata? {
        return metadata
    }

    fun setMetadata(metadata: Metadata) {
        this.metadata = metadata
    }

    fun getSource(): String? {
        return source
    }

    fun setSource(source: String) {
        this.source = source
    }

    fun getInReplyToStatusId(): Any? {
        return inReplyToStatusId
    }

    fun setInReplyToStatusId(inReplyToStatusId: Any) {
        this.inReplyToStatusId = inReplyToStatusId
    }

    fun getInReplyToStatusIdStr(): Any? {
        return inReplyToStatusIdStr
    }

    fun setInReplyToStatusIdStr(inReplyToStatusIdStr: Any) {
        this.inReplyToStatusIdStr = inReplyToStatusIdStr
    }

    fun getInReplyToUserId(): Any? {
        return inReplyToUserId
    }

    fun setInReplyToUserId(inReplyToUserId: Any) {
        this.inReplyToUserId = inReplyToUserId
    }

    fun getInReplyToUserIdStr(): Any? {
        return inReplyToUserIdStr
    }

    fun setInReplyToUserIdStr(inReplyToUserIdStr: Any) {
        this.inReplyToUserIdStr = inReplyToUserIdStr
    }

    fun getInReplyToScreenName(): Any? {
        return inReplyToScreenName
    }

    fun setInReplyToScreenName(inReplyToScreenName: Any) {
        this.inReplyToScreenName = inReplyToScreenName
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User) {
        this.user = user
    }

    fun getGeo(): Any? {
        return geo
    }

    fun setGeo(geo: Any) {
        this.geo = geo
    }

    fun getCoordinates(): Any? {
        return coordinates
    }

    fun setCoordinates(coordinates: Any) {
        this.coordinates = coordinates
    }

    fun getPlace(): Any? {
        return place
    }

    fun setPlace(place: Any) {
        this.place = place
    }

    fun getContributors(): Any? {
        return contributors
    }

    fun setContributors(contributors: Any) {
        this.contributors = contributors
    }

    fun getIsQuoteStatus(): Boolean? {
        return isQuoteStatus
    }

    fun setIsQuoteStatus(isQuoteStatus: Boolean?) {
        this.isQuoteStatus = isQuoteStatus
    }

    fun getQuotedStatusId(): Int? {
        return quotedStatusId
    }

    fun setQuotedStatusId(quotedStatusId: Int?) {
        this.quotedStatusId = quotedStatusId
    }

    fun getQuotedStatusIdStr(): String? {
        return quotedStatusIdStr
    }

    fun setQuotedStatusIdStr(quotedStatusIdStr: String) {
        this.quotedStatusIdStr = quotedStatusIdStr
    }

    fun getQuotedStatus(): QuotedStatus? {
        return quotedStatus
    }

    fun setQuotedStatus(quotedStatus: QuotedStatus) {
        this.quotedStatus = quotedStatus
    }

    fun getRetweetCount(): Int? {
        return retweetCount
    }

    fun setRetweetCount(retweetCount: Int?) {
        this.retweetCount = retweetCount
    }

    fun getFavoriteCount(): Int? {
        return favoriteCount
    }

    fun setFavoriteCount(favoriteCount: Int?) {
        this.favoriteCount = favoriteCount
    }

    fun getFavorited(): Boolean? {
        return favorited
    }

    fun setFavorited(favorited: Boolean?) {
        this.favorited = favorited
    }

    fun getRetweeted(): Boolean? {
        return retweeted
    }

    fun setRetweeted(retweeted: Boolean?) {
        this.retweeted = retweeted
    }

    fun getPossiblySensitive(): Boolean? {
        return possiblySensitive
    }

    fun setPossiblySensitive(possiblySensitive: Boolean?) {
        this.possiblySensitive = possiblySensitive
    }

    fun getLang(): String? {
        return lang
    }

    fun setLang(lang: String) {
        this.lang = lang
    }

}