package org.owntracks.android.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class FieldNameEntranceAndCoordinate: Serializable {
    @JsonProperty("fieldNameEntrance")
    var fieldNameEntrance: String? = null;

    @JsonProperty("coordinateEntrance")
    var coordinateEntrance: ArrayList<Double>? = null;
}