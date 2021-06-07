package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonProperty

class AvailableParkingSpot {
    @JsonProperty("keyIDFreeParking")
    var keyIDFreeParking: String? = null;

    @JsonProperty("fieldNameFreeParking")
    var fieldNameFreeParking: String? = null;

    @JsonProperty("coordinate")
    var coordinate: ArrayList<Any>? = null;
}