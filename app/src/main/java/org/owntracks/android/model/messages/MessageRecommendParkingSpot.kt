package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.owntracks.android.model.EntrancePosition
import kotlin.collections.ArrayList

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageRecommendParkingSpot : MessageBase() {
    //Import characters this message
    @JsonProperty("time")
    var time: String? = null

    @JsonProperty("date")
    var date: String? = null

    @JsonProperty("tst")
    var tst: Long? = null

    @JsonProperty("keyID")
    var keyID: String? = null

    @JsonProperty("currentLat")
    var currentLat: Double? = null

    @JsonProperty("currentLon")
    var currentLon: Double? = null

    @JsonProperty("fieldName")
    var fieldName: String? = null

    @JsonProperty("entrancePosition")
    var entrancePosition: ArrayList<EntrancePosition>? = null

    @JsonProperty("numberAvailableParkingSpot")
    var numberAvailableParkingSpot: Int? = null

    companion object {
        const val TYPE = "RecommendParkingSpot"
    }
}