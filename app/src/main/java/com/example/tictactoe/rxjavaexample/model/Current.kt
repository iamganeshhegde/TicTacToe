package com.example.tictactoe.rxjavaexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Current {

    @SerializedName("last_updated_epoch")
    @Expose
    private var lastUpdatedEpoch: Int? = null
    @SerializedName("last_updated")
    @Expose
    private var lastUpdated: String? = null
    @SerializedName("temp_c")
    @Expose
    private var tempC: Double? = null
    @SerializedName("temp_f")
    @Expose
    private var tempF: Double? = null
    @SerializedName("is_day")
    @Expose
    private var isDay: Int? = null
    @SerializedName("condition")
    @Expose
    private var condition: Condition? = null
    @SerializedName("wind_mph")
    @Expose
    private var windMph: Double? = null
    @SerializedName("wind_kph")
    @Expose
    private var windKph: Double? = null
    @SerializedName("wind_degree")
    @Expose
    private var windDegree: Int? = null
    @SerializedName("wind_dir")
    @Expose
    private var windDir: String? = null
    @SerializedName("pressure_mb")
    @Expose
    private var pressureMb: Double? = null
    @SerializedName("pressure_in")
    @Expose
    private var pressureIn: Double? = null
    @SerializedName("precip_mm")
    @Expose
    private var precipMm: Double? = null
    @SerializedName("precip_in")
    @Expose
    private var precipIn: Double? = null
    @SerializedName("humidity")
    @Expose
    private var humidity: Int? = null
    @SerializedName("cloud")
    @Expose
    private var cloud: Int? = null
    @SerializedName("feelslike_c")
    @Expose
    private var feelslikeC: Double? = null
    @SerializedName("feelslike_f")
    @Expose
    private var feelslikeF: Double? = null
    @SerializedName("vis_km")
    @Expose
    private var visKm: Double? = null
    @SerializedName("vis_miles")
    @Expose
    private var visMiles: Double? = null
    @SerializedName("uv")
    @Expose
    private var uv: Double? = null
    @SerializedName("gust_mph")
    @Expose
    private var gustMph: Double? = null
    @SerializedName("gust_kph")
    @Expose
    private var gustKph: Double? = null

    fun getLastUpdatedEpoch(): Int? {
        return lastUpdatedEpoch
    }

    fun setLastUpdatedEpoch(lastUpdatedEpoch: Int?) {
        this.lastUpdatedEpoch = lastUpdatedEpoch
    }

    fun getLastUpdated(): String? {
        return lastUpdated
    }

    fun setLastUpdated(lastUpdated: String) {
        this.lastUpdated = lastUpdated
    }

    fun getTempC(): Double? {
        return tempC
    }

    fun setTempC(tempC: Double?) {
        this.tempC = tempC
    }

    fun getTempF(): Double? {
        return tempF
    }

    fun setTempF(tempF: Double?) {
        this.tempF = tempF
    }

    fun getIsDay(): Int? {
        return isDay
    }

    fun setIsDay(isDay: Int?) {
        this.isDay = isDay
    }

    fun getCondition(): Condition? {
        return condition
    }

    fun setCondition(condition: Condition) {
        this.condition = condition
    }

    fun getWindMph(): Double? {
        return windMph
    }

    fun setWindMph(windMph: Double?) {
        this.windMph = windMph
    }

    fun getWindKph(): Double? {
        return windKph
    }

    fun setWindKph(windKph: Double?) {
        this.windKph = windKph
    }

    fun getWindDegree(): Int? {
        return windDegree
    }

    fun setWindDegree(windDegree: Int?) {
        this.windDegree = windDegree
    }

    fun getWindDir(): String? {
        return windDir
    }

    fun setWindDir(windDir: String) {
        this.windDir = windDir
    }

    fun getPressureMb(): Double? {
        return pressureMb
    }

    fun setPressureMb(pressureMb: Double?) {
        this.pressureMb = pressureMb
    }

    fun getPressureIn(): Double? {
        return pressureIn
    }

    fun setPressureIn(pressureIn: Double?) {
        this.pressureIn = pressureIn
    }

    fun getPrecipMm(): Double? {
        return precipMm
    }

    fun setPrecipMm(precipMm: Double?) {
        this.precipMm = precipMm
    }

    fun getPrecipIn(): Double? {
        return precipIn
    }

    fun setPrecipIn(precipIn: Double?) {
        this.precipIn = precipIn
    }

    fun getHumidity(): Int? {
        return humidity
    }

    fun setHumidity(humidity: Int?) {
        this.humidity = humidity
    }

    fun getCloud(): Int? {
        return cloud
    }

    fun setCloud(cloud: Int?) {
        this.cloud = cloud
    }

    fun getFeelslikeC(): Double? {
        return feelslikeC
    }

    fun setFeelslikeC(feelslikeC: Double?) {
        this.feelslikeC = feelslikeC
    }

    fun getFeelslikeF(): Double? {
        return feelslikeF
    }

    fun setFeelslikeF(feelslikeF: Double?) {
        this.feelslikeF = feelslikeF
    }

    fun getVisKm(): Double? {
        return visKm
    }

    fun setVisKm(visKm: Double?) {
        this.visKm = visKm
    }

    fun getVisMiles(): Double? {
        return visMiles
    }

    fun setVisMiles(visMiles: Double?) {
        this.visMiles = visMiles
    }

    fun getUv(): Double? {
        return uv
    }

    fun setUv(uv: Double?) {
        this.uv = uv
    }

    fun getGustMph(): Double? {
        return gustMph
    }

    fun setGustMph(gustMph: Double?) {
        this.gustMph = gustMph
    }

    fun getGustKph(): Double? {
        return gustKph
    }

    fun setGustKph(gustKph: Double?) {
        this.gustKph = gustKph
    }
}