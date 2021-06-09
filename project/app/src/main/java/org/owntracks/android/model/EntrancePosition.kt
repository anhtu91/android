package org.owntracks.android.model

import com.fasterxml.jackson.annotation.JsonProperty

class EntrancePosition {
    @JsonProperty("keyIDEntranceFreeParking")
    var keyIDEntranceFreeParking: String? = null;

    @JsonProperty("fieldNameEntranceFreeParking")
    var fieldNameEntranceFreeParking: String? = null;

    @JsonProperty("coordinateEntranceFreeParking")
    var coordinateEntranceFreeParking: ArrayList<Any>? = null;
}