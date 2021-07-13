package org.owntracks.android.model.messages

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.owntracks.android.support.Preferences


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class MessageCancelRequestWaypointToSelectedEntrance(private val dep: MessageWithCreatedAt = MessageCreatedAtNow(RealClock())) : MessageBase(), MessageWithCreatedAt by dep {
    @JsonProperty("cancelWaypointToEntrance")
    var cancelWaypointToEntrance: Boolean? = null

    override fun addMqttPreferences(preferences: Preferences) {
        topic = preferences.pubTopicCancelRequestWaypointToSelectedEntrance
        qos = preferences.pubQoSCancelRequestWaypointToSelectedEntrance
        retained = preferences.pubRetainCancelRequestWaypointToSelectedEntrance
    }

    companion object {
        const val TYPE = "MessageCancelRequestWaypointToSelectedEntrance"
    }
}