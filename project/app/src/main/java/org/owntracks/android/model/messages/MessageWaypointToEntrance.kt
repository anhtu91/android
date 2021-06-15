package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageWaypointToEntrance : MessageBase() {
    //Import characters this message
    @JsonProperty("coordinatesArray")
    var coordinatesArray: ArrayList<ArrayList<Double>>? = null

    @JsonProperty("distance")
    var distance: Double? = null

    @JsonProperty("duration")
    var duration: Double? = null

    @JsonProperty("selectedKeyIDEntrance")
    var selectedKeyIDEntrance: String? = null

    @JsonProperty("selectedFieldNameEntrance")
    var selectedFieldNameEntrance: String? = null

    @JsonProperty("latitudeSelectedEntrance")
    var latitudeSelectedEntrance: Double? = null

    @JsonProperty("longitudeSelectedEntrance")
    var longitudeSelectedEntrance: Double? = null

    companion object {
        const val TYPE = "WaypointToEntrance"
    }
}