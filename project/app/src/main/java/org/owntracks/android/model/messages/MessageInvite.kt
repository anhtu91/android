package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageInvite : MessageBase() {

    @JsonProperty("keyIDInvite")
    var keyIDInvite: String? = null

    @JsonProperty("fieldNameInvite")
    var fieldNameInvite: String? = null

    @JsonProperty("dateInvite")
    var dateInvite: String? = null

    @JsonProperty("timeInvite")
    var timeInvite: String? = null

    @JsonProperty("emailInvite")
    var emailInvite: String? = null

    companion object {
        const val TYPE = "Invite"
    }
}