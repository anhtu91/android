package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005\u00a2\u0006\u0002\u0010\u0002RH\u0010\u0003\u001a,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0004j\u001a\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0018\u0001`\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\"\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR\"\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR \u0010\u001a\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010 \u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001f\u00a8\u0006$"}, d2 = {"Lorg/owntracks/android/model/messages/MessageWaypointToEntrance;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "coordinatesArray", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getCoordinatesArray", "()Ljava/util/ArrayList;", "setCoordinatesArray", "(Ljava/util/ArrayList;)V", "distance", "getDistance", "()Ljava/lang/Double;", "setDistance", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "duration", "getDuration", "setDuration", "latitudeSelectedEntrance", "getLatitudeSelectedEntrance", "setLatitudeSelectedEntrance", "longitudeSelectedEntrance", "getLongitudeSelectedEntrance", "setLongitudeSelectedEntrance", "selectedFieldNameEntrance", "", "getSelectedFieldNameEntrance", "()Ljava/lang/String;", "setSelectedFieldNameEntrance", "(Ljava/lang/String;)V", "selectedKeyIDEntrance", "getSelectedKeyIDEntrance", "setSelectedKeyIDEntrance", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public final class MessageWaypointToEntrance extends org.owntracks.android.model.messages.MessageBase {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "coordinatesArray")
    private java.util.ArrayList<java.util.ArrayList<java.lang.Double>> coordinatesArray;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "distance")
    private java.lang.Double distance;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "duration")
    private java.lang.Double duration;
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
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "WaypointToEntrance";
    public static final org.owntracks.android.model.messages.MessageWaypointToEntrance.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<java.util.ArrayList<java.lang.Double>> getCoordinatesArray() {
        return null;
    }
    
    public final void setCoordinatesArray(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<java.util.ArrayList<java.lang.Double>> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getDistance() {
        return null;
    }
    
    public final void setDistance(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getDuration() {
        return null;
    }
    
    public final void setDuration(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
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
    
    public MessageWaypointToEntrance() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/model/messages/MessageWaypointToEntrance$Companion;", "", "()V", "TYPE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}