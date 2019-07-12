package com.example.tictactoe.twitterExample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class User_ {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("id_str")
    @Expose
    private var idStr: String? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("screen_name")
    @Expose
    private var screenName: String? = null
    @SerializedName("location")
    @Expose
    private var location: String? = null
    @SerializedName("description")
    @Expose
    private var description: String? = null
    @SerializedName("url")
    @Expose
    private var url: String? = null
    @SerializedName("entities")
    @Expose
    private var entities: Entities___? = null
    @SerializedName("protected")
    @Expose
    private var _protected: Boolean? = null
    @SerializedName("followers_count")
    @Expose
    private var followersCount: Int? = null
    @SerializedName("friends_count")
    @Expose
    private var friendsCount: Int? = null
    @SerializedName("listed_count")
    @Expose
    private var listedCount: Int? = null
    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null
    @SerializedName("favourites_count")
    @Expose
    private var favouritesCount: Int? = null
    @SerializedName("utc_offset")
    @Expose
    private var utcOffset: Any? = null
    @SerializedName("time_zone")
    @Expose
    private var timeZone: Any? = null
    @SerializedName("geo_enabled")
    @Expose
    private var geoEnabled: Boolean? = null
    @SerializedName("verified")
    @Expose
    private var verified: Boolean? = null
    @SerializedName("statuses_count")
    @Expose
    private var statusesCount: Int? = null
    @SerializedName("lang")
    @Expose
    private var lang: String? = null
    @SerializedName("contributors_enabled")
    @Expose
    private var contributorsEnabled: Boolean? = null
    @SerializedName("is_translator")
    @Expose
    private var isTranslator: Boolean? = null
    @SerializedName("is_translation_enabled")
    @Expose
    private var isTranslationEnabled: Any? = null
    @SerializedName("profile_background_color")
    @Expose
    private var profileBackgroundColor: String? = null
    @SerializedName("profile_background_image_url")
    @Expose
    private var profileBackgroundImageUrl: String? = null
    @SerializedName("profile_background_image_url_https")
    @Expose
    private var profileBackgroundImageUrlHttps: String? = null
    @SerializedName("profile_background_tile")
    @Expose
    private var profileBackgroundTile: Any? = null
    @SerializedName("profile_image_url")
    @Expose
    private var profileImageUrl: String? = null
    @SerializedName("profile_image_url_https")
    @Expose
    private var profileImageUrlHttps: String? = null
    @SerializedName("profile_banner_url")
    @Expose
    private var profileBannerUrl: String? = null
    @SerializedName("profile_link_color")
    @Expose
    private var profileLinkColor: String? = null
    @SerializedName("profile_sidebar_border_color")
    @Expose
    private var profileSidebarBorderColor: String? = null
    @SerializedName("profile_sidebar_fill_color")
    @Expose
    private var profileSidebarFillColor: String? = null
    @SerializedName("profile_text_color")
    @Expose
    private var profileTextColor: String? = null
    @SerializedName("profile_use_background_image")
    @Expose
    private var profileUseBackgroundImage: Any? = null
    @SerializedName("has_extended_profile")
    @Expose
    private var hasExtendedProfile: Any? = null
    @SerializedName("default_profile")
    @Expose
    private var defaultProfile: Boolean? = null
    @SerializedName("default_profile_image")
    @Expose
    private var defaultProfileImage: Boolean? = null
    @SerializedName("following")
    @Expose
    private var following: Boolean? = null
    @SerializedName("follow_request_sent")
    @Expose
    private var followRequestSent: Boolean? = null
    @SerializedName("notifications")
    @Expose
    private var notifications: Boolean? = null
    @SerializedName("translator_type")
    @Expose
    private var translatorType: String? = null

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

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getScreenName(): String? {
        return screenName
    }

    fun setScreenName(screenName: String) {
        this.screenName = screenName
    }

    fun getLocation(): String? {
        return location
    }

    fun setLocation(location: String) {
        this.location = location
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getEntities(): Entities___? {
        return entities
    }

    fun setEntities(entities: Entities___) {
        this.entities = entities
    }

    fun getProtected(): Boolean? {
        return _protected
    }

    fun setProtected(_protected: Boolean?) {
        this._protected = _protected
    }

    fun getFollowersCount(): Int? {
        return followersCount
    }

    fun setFollowersCount(followersCount: Int?) {
        this.followersCount = followersCount
    }

    fun getFriendsCount(): Int? {
        return friendsCount
    }

    fun setFriendsCount(friendsCount: Int?) {
        this.friendsCount = friendsCount
    }

    fun getListedCount(): Int? {
        return listedCount
    }

    fun setListedCount(listedCount: Int?) {
        this.listedCount = listedCount
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String) {
        this.createdAt = createdAt
    }

    fun getFavouritesCount(): Int? {
        return favouritesCount
    }

    fun setFavouritesCount(favouritesCount: Int?) {
        this.favouritesCount = favouritesCount
    }

    fun getUtcOffset(): Any? {
        return utcOffset
    }

    fun setUtcOffset(utcOffset: Any) {
        this.utcOffset = utcOffset
    }

    fun getTimeZone(): Any? {
        return timeZone
    }

    fun setTimeZone(timeZone: Any) {
        this.timeZone = timeZone
    }

    fun getGeoEnabled(): Boolean? {
        return geoEnabled
    }

    fun setGeoEnabled(geoEnabled: Boolean?) {
        this.geoEnabled = geoEnabled
    }

    fun getVerified(): Boolean? {
        return verified
    }

    fun setVerified(verified: Boolean?) {
        this.verified = verified
    }

    fun getStatusesCount(): Int? {
        return statusesCount
    }

    fun setStatusesCount(statusesCount: Int?) {
        this.statusesCount = statusesCount
    }

    fun getLang(): String? {
        return lang
    }

    fun setLang(lang: String) {
        this.lang = lang
    }

    fun getContributorsEnabled(): Boolean? {
        return contributorsEnabled
    }

    fun setContributorsEnabled(contributorsEnabled: Boolean?) {
        this.contributorsEnabled = contributorsEnabled
    }

    fun getIsTranslator(): Boolean? {
        return isTranslator
    }

    fun setIsTranslator(isTranslator: Boolean?) {
        this.isTranslator = isTranslator
    }

    fun getIsTranslationEnabled(): Any? {
        return isTranslationEnabled
    }

    fun setIsTranslationEnabled(isTranslationEnabled: Any) {
        this.isTranslationEnabled = isTranslationEnabled
    }

    fun getProfileBackgroundColor(): String? {
        return profileBackgroundColor
    }

    fun setProfileBackgroundColor(profileBackgroundColor: String) {
        this.profileBackgroundColor = profileBackgroundColor
    }

    fun getProfileBackgroundImageUrl(): String? {
        return profileBackgroundImageUrl
    }

    fun setProfileBackgroundImageUrl(profileBackgroundImageUrl: String) {
        this.profileBackgroundImageUrl = profileBackgroundImageUrl
    }

    fun getProfileBackgroundImageUrlHttps(): String? {
        return profileBackgroundImageUrlHttps
    }

    fun setProfileBackgroundImageUrlHttps(profileBackgroundImageUrlHttps: String) {
        this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps
    }

    fun getProfileBackgroundTile(): Any? {
        return profileBackgroundTile
    }

    fun setProfileBackgroundTile(profileBackgroundTile: Any) {
        this.profileBackgroundTile = profileBackgroundTile
    }

    fun getProfileImageUrl(): String? {
        return profileImageUrl
    }

    fun setProfileImageUrl(profileImageUrl: String) {
        this.profileImageUrl = profileImageUrl
    }

    fun getProfileImageUrlHttps(): String? {
        return profileImageUrlHttps
    }

    fun setProfileImageUrlHttps(profileImageUrlHttps: String) {
        this.profileImageUrlHttps = profileImageUrlHttps
    }

    fun getProfileBannerUrl(): String? {
        return profileBannerUrl
    }

    fun setProfileBannerUrl(profileBannerUrl: String) {
        this.profileBannerUrl = profileBannerUrl
    }

    fun getProfileLinkColor(): String? {
        return profileLinkColor
    }

    fun setProfileLinkColor(profileLinkColor: String) {
        this.profileLinkColor = profileLinkColor
    }

    fun getProfileSidebarBorderColor(): String? {
        return profileSidebarBorderColor
    }

    fun setProfileSidebarBorderColor(profileSidebarBorderColor: String) {
        this.profileSidebarBorderColor = profileSidebarBorderColor
    }

    fun getProfileSidebarFillColor(): String? {
        return profileSidebarFillColor
    }

    fun setProfileSidebarFillColor(profileSidebarFillColor: String) {
        this.profileSidebarFillColor = profileSidebarFillColor
    }

    fun getProfileTextColor(): String? {
        return profileTextColor
    }

    fun setProfileTextColor(profileTextColor: String) {
        this.profileTextColor = profileTextColor
    }

    fun getProfileUseBackgroundImage(): Any? {
        return profileUseBackgroundImage
    }

    fun setProfileUseBackgroundImage(profileUseBackgroundImage: Any) {
        this.profileUseBackgroundImage = profileUseBackgroundImage
    }

    fun getHasExtendedProfile(): Any? {
        return hasExtendedProfile
    }

    fun setHasExtendedProfile(hasExtendedProfile: Any) {
        this.hasExtendedProfile = hasExtendedProfile
    }

    fun getDefaultProfile(): Boolean? {
        return defaultProfile
    }

    fun setDefaultProfile(defaultProfile: Boolean?) {
        this.defaultProfile = defaultProfile
    }

    fun getDefaultProfileImage(): Boolean? {
        return defaultProfileImage
    }

    fun setDefaultProfileImage(defaultProfileImage: Boolean?) {
        this.defaultProfileImage = defaultProfileImage
    }

    fun getFollowing(): Boolean? {
        return following
    }

    fun setFollowing(following: Boolean?) {
        this.following = following
    }

    fun getFollowRequestSent(): Boolean? {
        return followRequestSent
    }

    fun setFollowRequestSent(followRequestSent: Boolean?) {
        this.followRequestSent = followRequestSent
    }

    fun getNotifications(): Boolean? {
        return notifications
    }

    fun setNotifications(notifications: Boolean?) {
        this.notifications = notifications
    }

    fun getTranslatorType(): String? {
        return translatorType
    }

    fun setTranslatorType(translatorType: String) {
        this.translatorType = translatorType
    }
}