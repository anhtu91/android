package org.owntracks.android.model.messages

import androidx.databinding.Bindable
import com.fasterxml.jackson.annotation.*
import org.owntracks.android.model.BatteryStatus

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageParkplatz : MessageBase() {
    //Import characters this message
    @JsonProperty("userID")
    var userID: String? = null

    @JsonProperty("hashCode")
    var hashCode = 0

    @JsonProperty("time")
    var time: String? = null

    @JsonProperty("date")
    var date: String? = null

    @JsonProperty("keyID")
    var keyID: String? = null

    @JsonProperty("fieldName")
    var fieldName: String? = null

    @JsonProperty("numberAvailableParkingSlot")
    var numberAvailableParkingSlot: String? = null

    @JsonProperty("availableParkingSlot")
    var availableParkingSlot: String? = null

    companion object {
        const val TYPE = "Parkplatz"
    }
}