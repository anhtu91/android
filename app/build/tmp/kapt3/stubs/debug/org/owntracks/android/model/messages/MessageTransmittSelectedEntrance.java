package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.owntracks.android.support.Preferences;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 &2\u00020\u00012\u00020\u0002:\u0001&B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016R\u0012\u0010\u0005\u001a\u00020\u0006X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0010\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR\"\u0010\u0016\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR \u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\u001f\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001e\u00a8\u0006\'"}, d2 = {"Lorg/owntracks/android/model/messages/MessageTransmittSelectedEntrance;", "Lorg/owntracks/android/model/messages/MessageBase;", "Lorg/owntracks/android/model/messages/MessageWithCreatedAt;", "dep", "(Lorg/owntracks/android/model/messages/MessageWithCreatedAt;)V", "created_at", "", "getCreated_at", "()J", "currentLatitude", "", "getCurrentLatitude", "()Ljava/lang/Double;", "setCurrentLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "currentLongitude", "getCurrentLongitude", "setCurrentLongitude", "latitudeSelectedEntrance", "getLatitudeSelectedEntrance", "setLatitudeSelectedEntrance", "longitudeSelectedEntrance", "getLongitudeSelectedEntrance", "setLongitudeSelectedEntrance", "selectedFieldNameEntrance", "", "getSelectedFieldNameEntrance", "()Ljava/lang/String;", "setSelectedFieldNameEntrance", "(Ljava/lang/String;)V", "selectedKeyIDEntrance", "getSelectedKeyIDEntrance", "setSelectedKeyIDEntrance", "addMqttPreferences", "", "preferences", "Lorg/owntracks/android/support/Preferences;", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
public final class MessageTransmittSelectedEntrance extends org.owntracks.android.model.messages.MessageBase implements org.owntracks.android.model.messages.MessageWithCreatedAt {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "selectedKeyIDEntrance")
    private java.lang.String selectedKeyIDEntrance;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "selectedFieldNameEntrance")
    private java.lang.String selectedFieldNameEntrance;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "latitudeSelectedEntrance")
    private java.lang.Double latitudeSelectedEntrance;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "longitudeSelectedEntrance")
    private java.lang.Double longitudeSelectedEntrance;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "currentLatitude")
    private java.lang.Double currentLatitude;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "currentLongitude")
    private java.lang.Double currentLongitude;
    private final org.owntracks.android.model.messages.MessageWithCreatedAt dep = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "MessageTransmittSelectedEntrance";
    public static final org.owntracks.android.model.messages.MessageTransmittSelectedEntrance.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelectedKeyIDEntrance() {
        return null;
    }
    
    public final void setSelectedKeyIDEntrance(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelectedFieldNameEntrance() {
        return null;
    }
    
    public final void setSelectedFieldNameEntrance(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLatitudeSelectedEntrance() {
        return null;
    }
    
    public final void setLatitudeSelectedEntrance(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLongitudeSelectedEntrance() {
        return null;
    }
    
    public final void setLongitudeSelectedEntrance(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getCurrentLatitude() {
        return null;
    }
    
    public final void setCurrentLatitude(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getCurrentLongitude() {
        return null;
    }
    
    public final void setCurrentLongitude(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @java.lang.Override()
    public void addMqttPreferences(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.support.Preferences preferences) {
    }
    
    public MessageTransmittSelectedEntrance(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.model.messages.MessageWithCreatedAt dep) {
        super();
    }
    
    public MessageTransmittSelectedEntrance() {
        super();
    }
    
    @java.lang.Override()
    public long getCreated_at() {
        return 0L;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/model/messages/MessageTransmittSelectedEntrance$Companion;", "", "()V", "TYPE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}