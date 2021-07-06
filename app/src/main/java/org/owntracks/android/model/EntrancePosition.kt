package org.owntracks.android.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class EntrancePosition: Serializable {
    @JsonProperty("keyIDEntrance")
    var keyIDEntrance: String? = null;

    @JsonProperty("fieldNameEntranceAndCoordinate")
    var FieldNameEntranceAndCoordinate: ArrayList<FieldNameEntranceAndCoordinate>? = null;
}