package org.owntracks.android.ui.map;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import org.owntracks.android.model.CoordinateEntrance;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageWaypointToEntrance;
import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u00a8\u0006\u0004"}, d2 = {"Lorg/owntracks/android/ui/map/MapMvvm;", "", "View", "ViewModel", "app_debug"})
public abstract interface MapMvvm {
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\b\u0010\u0012\u001a\u00020\u0003H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&\u00a8\u0006\u0016"}, d2 = {"Lorg/owntracks/android/ui/map/MapMvvm$View;", "Lorg/owntracks/android/ui/base/view/MvvmView;", "addMarkerEntranceToMap", "", "messageSelectedEntrance", "Lorg/owntracks/android/model/CoordinateEntrance;", "cancelThreadSendWaypointToEntrance", "clearMarkers", "enableLocationMenus", "removeMarker", "removePolyline", "runThreadSelectedEntrance", "setBottomSheetCollapsed", "setBottomSheetExpanded", "setBottomSheetHidden", "updateMarker", "contact", "Lorg/owntracks/android/model/FusedContact;", "updateMonitoringModeMenu", "updateWaypointToEntrance", "messageWaypointToEntrance", "Lorg/owntracks/android/model/messages/MessageWaypointToEntrance;", "app_debug"})
    public static abstract interface View extends org.owntracks.android.ui.base.view.MvvmView {
        
        public abstract void setBottomSheetExpanded();
        
        public abstract void setBottomSheetCollapsed();
        
        public abstract void setBottomSheetHidden();
        
        public abstract void updateMarker(@org.jetbrains.annotations.Nullable()
        org.owntracks.android.model.FusedContact contact);
        
        public abstract void removeMarker();
        
        public abstract void clearMarkers();
        
        public abstract void enableLocationMenus();
        
        public abstract void updateMonitoringModeMenu();
        
        public abstract void updateWaypointToEntrance(@org.jetbrains.annotations.NotNull()
        org.owntracks.android.model.messages.MessageWaypointToEntrance messageWaypointToEntrance);
        
        public abstract void runThreadSelectedEntrance();
        
        public abstract void cancelThreadSendWaypointToEntrance();
        
        public abstract void addMarkerEntranceToMap(@org.jetbrains.annotations.NotNull()
        org.owntracks.android.model.CoordinateEntrance messageSelectedEntrance);
        
        public abstract void removePolyline();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u0000*\n\b\u0000\u0010\u0001*\u0004\u0018\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\b\u0010#\u001a\u00020\u0006H&J\b\u0010$\u001a\u00020%H&J\b\u0010&\u001a\u00020%H&J\b\u0010\'\u001a\u00020%H&J\b\u0010(\u001a\u00020%H&J\b\u0010)\u001a\u00020%H&J\b\u0010*\u001a\u00020%H&R\u001c\u0010\u0004\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r8gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u0004\u0018\u00010\nX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\f\u0012\u0006\u0012\u0004\u0018\u00010!\u0018\u00010\u0005X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\b\u00a8\u0006+"}, d2 = {"Lorg/owntracks/android/ui/map/MapMvvm$ViewModel;", "V", "Lorg/owntracks/android/ui/base/view/MvvmView;", "Lorg/owntracks/android/ui/base/viewmodel/MvvmViewModel;", "bottomSheetHidden", "Landroidx/lifecycle/LiveData;", "", "getBottomSheetHidden", "()Landroidx/lifecycle/LiveData;", "center", "Lcom/google/android/gms/maps/model/LatLng;", "getCenter", "contacts", "", "Lorg/owntracks/android/model/FusedContact;", "getContacts", "()Ljava/util/Collection;", "currentLocation", "getCurrentLocation", "()Lcom/google/android/gms/maps/model/LatLng;", "mapLocationSource", "Lcom/google/android/gms/maps/LocationSource;", "getMapLocationSource", "()Lcom/google/android/gms/maps/LocationSource;", "onMapClickListener", "Lcom/google/android/gms/maps/GoogleMap$OnMapClickListener;", "getOnMapClickListener", "()Lcom/google/android/gms/maps/GoogleMap$OnMapClickListener;", "onMarkerClickListener", "Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;", "getOnMarkerClickListener", "()Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;", "waypoint", "Lorg/owntracks/android/model/messages/MessageWaypointToEntrance;", "getWaypoint", "hasLocation", "onBottomSheetClick", "", "onBottomSheetLongClick", "onMapReady", "onMenuCenterDeviceClicked", "sendLocation", "stopRequestWaypointThread", "app_debug"})
    public static abstract interface ViewModel<V extends org.owntracks.android.ui.base.view.MvvmView> extends org.owntracks.android.ui.base.viewmodel.MvvmViewModel<V> {
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.model.LatLng getCurrentLocation();
        
        @org.jetbrains.annotations.Nullable()
        @androidx.databinding.Bindable()
        public abstract java.util.Collection<org.owntracks.android.model.FusedContact> getContacts();
        
        public abstract void onBottomSheetLongClick();
        
        public abstract void onBottomSheetClick();
        
        public abstract void onMenuCenterDeviceClicked();
        
        public abstract void stopRequestWaypointThread();
        
        public abstract boolean hasLocation();
        
        public abstract void onMapReady();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.LocationSource getMapLocationSource();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.GoogleMap.OnMapClickListener getOnMapClickListener();
        
        @org.jetbrains.annotations.Nullable()
        public abstract com.google.android.gms.maps.GoogleMap.OnMarkerClickListener getOnMarkerClickListener();
        
        @org.jetbrains.annotations.Nullable()
        public abstract androidx.lifecycle.LiveData<java.lang.Boolean> getBottomSheetHidden();
        
        @org.jetbrains.annotations.Nullable()
        public abstract androidx.lifecycle.LiveData<com.google.android.gms.maps.model.LatLng> getCenter();
        
        @org.jetbrains.annotations.Nullable()
        public abstract androidx.lifecycle.LiveData<org.owntracks.android.model.messages.MessageWaypointToEntrance> getWaypoint();
        
        public abstract void sendLocation();
    }
}