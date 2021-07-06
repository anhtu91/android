package org.owntracks.android.model.messages;

import com.fasterxml.jackson.annotation.*;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\n"}, d2 = {"Lorg/owntracks/android/model/messages/MessageParkplatz;", "Lorg/owntracks/android/model/messages/MessageBase;", "()V", "jwt", "", "getJwt", "()Ljava/lang/String;", "setJwt", "(Ljava/lang/String;)V", "Companion", "app_debug"})
@com.fasterxml.jackson.annotation.JsonInclude(value = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY)
@com.fasterxml.jackson.annotation.JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "_type")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public final class MessageParkplatz extends org.owntracks.android.model.messages.MessageBase {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "jsonWebToken")
    private java.lang.String jwt;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE = "Parkplatz";
    public static final org.owntracks.android.model.messages.MessageParkplatz.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getJwt() {
        return null;
    }
    
    public final void setJwt(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public MessageParkplatz() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lorg/owntracks/android/model/messages/MessageParkplatz$Companion;", "", "()V", "TYPE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}