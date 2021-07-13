package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.owntracks.android.support.Preferences;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00142\u00020\u00012\u00020\u0002:\u0001\u0014B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0012\u0010\f\u001a\u00020\rX\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lorg/owntracks/android/model/messages/MessageCancelRequestWaypointToSelectedEntrance;", "Lorg/owntracks/android/model/messages/MessageBase;", "Lorg/owntracks/android/model/messages/MessageWithCreatedAt;", "dep", "(Lorg/owntracks/android/model/messages/MessageWithCreatedAt;)V", "cancelWaypointToEntrance", "", "getCancelWaypointToEntrance", "()Ljava/lang/Boolean;", "setCancelWaypointToEntrance", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "created_at", "", "getCreated_at", "()J", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public final class MessageCancelRequestWaypointToSelectedEntrance extends org.owntracks.android.model.messages.MessageBase implements org.owntracks.android.model.messages.MessageWithCreatedAt {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "cancelWaypointToEntrance")
    private java.lang.Boolean cancelWaypointToEntrance;
    private final org.owntracks.android.model.messages.MessageWithCreatedAt dep = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "MessageCancelRequestWaypointToSelectedEntrance";
    public static final org.owntracks.android.model.messages.MessageCancelRequestWaypointToSelectedEntrance.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getCancelWaypointToEntrance() {
        return null;
    }
    
    public final void setCancelWaypointToEntrance(@org.jetbrains.annotations.Nullable()
    java.lang.Boolean p0) {
    }
    
    @java.lang.Override()
    public void addMqttPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
    }
    
    public MessageCancelRequestWaypointToSelectedEntrance(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageWithCreatedAt dep) {
        super();
    }
    
    public MessageCancelRequestWaypointToSelectedEntrance() {
        super();
    }
    
    @java.lang.Override()
    public long getCreated_at() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/model/messages/MessageCancelRequestWaypointToSelectedEntrance$Companion;", "", "()V", "TYPE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}