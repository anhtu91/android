package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageWaypointToEntrance : MessageBase(){

    @JsonProperty("coordinatesArray")
    var coordinatesArray: ArrayList<ArrayList<Double>>? = null

    @JsonProperty("distance")
    var distance: Double? = null

    @JsonProperty("duration")
    var duration: Int? = null

    companion object {
        const val TYPE = "WaypointToEntrance"
    }
}