package org.owntracks.android.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R2\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/owntracks/android/model/EntrancePosition;", "Ljava/io/Serializable;", "()V", "FieldNameEntranceAndCoordinate", "Ljava/util/ArrayList;", "Lorg/owntracks/android/model/FieldNameEntranceAndCoordinate;", "Lkotlin/collections/ArrayList;", "getFieldNameEntranceAndCoordinate", "()Ljava/util/ArrayList;", "setFieldNameEntranceAndCoordinate", "(Ljava/util/ArrayList;)V", "keyIDEntrance", "", "getKeyIDEntrance", "()Ljava/lang/String;", "setKeyIDEntrance", "(Ljava/lang/String;)V", "app_debug"})
public final class EntrancePosition implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "keyIDEntrance")
    private java.lang.String keyIDEntrance;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonProperty(value = "fieldNameEntranceAndCoordinate")
    private java.util.ArrayList<org.owntracks.android.model.FieldNameEntranceAndCoordinate> FieldNameEntranceAndCoordinate;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getKeyIDEntrance() {
        return null;
    }
    
    public final void setKeyIDEntrance(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<org.owntracks.android.model.FieldNameEntranceAndCoordinate> getFieldNameEntranceAndCoordinate() {
        return null;
    }
    
    public final void setFieldNameEntranceAndCoordinate(@org.jetbrains.annotations.Nullable()
    java.util.ArrayList<org.owntracks.android.model.FieldNameEntranceAndCoordinate> p0) {
    }
    
    public EntrancePosition() {
        super();
    }
}