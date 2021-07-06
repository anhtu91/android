package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.owntracks.android.support.Preferences

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageTransmittSelectedEntrance(private val dep: MessageWithCreatedAt = MessageCreatedAtNow(RealClock())) : MessageBase(), MessageWithCreatedAt by dep {
    @JsonProperty("selectedKeyIDEntrance")
    var selectedKeyIDEntrance: String? = null

    @JsonProperty("selectedFieldNameEntrance")
    var selectedFieldNameEntrance: String? = null

    @JsonProperty("latitudeSelectedEntrance")
    var latitudeSelectedEntrance: Double? = null

    @JsonProperty("longitudeSelectedEntrance")
    var longitudeSelectedEntrance: Double? = null

    @JsonProperty("currentLatitude")
    var currentLatitude: Double? = null

    @JsonProperty("currentLongitude")
    var currentLongitude: Double? = null

    override fun addMqttPreferences(preferences: Preferences) {
        topic = preferences.pubTopicTransmittSelectedEntrance
        qos = preferences.pubQoSTransmittSelectedEntrance
        retained = preferences.pubRetainTransmittSelectedEntrance
    }

    companion object {
        const val TYPE = "MessageTransmittSelectedEntrance"
    }
}