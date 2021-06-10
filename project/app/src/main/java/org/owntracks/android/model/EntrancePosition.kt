package org.owntracks.android.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class EntrancePosition: Serializable {
    @JsonProperty("keyIDEntranceFreeParking")
    var keyIDEntranceFreeParking: String? = null;

    @JsonProperty("fieldNameEntranceFreeParking")
    var fieldNameEntranceFreeParking: String? = null;

    @JsonProperty("coordinateEntranceFreeParking")
    var coordinateEntranceFreeParking: ArrayList<Double>? = null;
}