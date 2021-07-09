package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.owntracks.android.model.ManagementAccountModel
import org.owntracks.android.model.SelectedParkingSpot

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageReceiveSelectedParking : MessageBase(){
    @JsonProperty("listSelectedParking")
    var listSelectedParking: ArrayList<SelectedParkingSpot>? = null

    companion object {
        const val TYPE = "ReceiveSelectedParking"
    }
}