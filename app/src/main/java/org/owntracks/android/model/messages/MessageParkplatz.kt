package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageParkplatz : MessageBase() {
    //Import characters this message
    @JsonProperty("jsonWebToken")
    var jwt: String? = null

    companion object {
        const val TYPE = "Parkplatz"
    }
}