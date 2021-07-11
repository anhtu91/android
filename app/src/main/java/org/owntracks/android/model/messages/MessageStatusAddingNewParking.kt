package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageStatusAddingNewParking: MessageBase() {
    @JsonProperty("result")
    var result: Boolean? = false

    @JsonProperty("keyID")
    var keyID: String? = null

    @JsonProperty("fieldName")
    var fieldName: String? = null

    companion object {
        const val TYPE = "ReceiveAddingParking"
    }
}