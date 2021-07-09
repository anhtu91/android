package org.owntracks.android.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class SelectedParkingSpot: Serializable {
    @JsonProperty("keyID")
    var keyID: String? = null;

    @JsonProperty("fieldName")
    var fieldName: String? = null;
}