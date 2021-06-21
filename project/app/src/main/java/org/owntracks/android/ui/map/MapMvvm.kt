package org.owntracks.android.ui.map

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng
import org.owntracks.android.model.CoordinateEntrance
import org.owntracks.android.model.FusedContact
import org.owntracks.android.model.messages.MessageWaypointToEntrance
import org.owntracks.android.ui.base.view.MvvmView
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel

interface MapMvvm {
    interface View : MvvmView {
        fun setBottomSheetExpanded()
        fun setBottomSheetCollapsed()
        fun setBottomSheetHidden()
        fun updateMarker(contact: FusedContact?)
        fun removeMarker()
        fun clearMarkers()
        fun enableLocationMenus()
        fun updateMonitoringModeMenu()
        fun updateWaypointToEntrance(messageWaypointToEntrance: MessageWaypointToEntrance)
        fun runThreadSelectedEntrance()
        fun cancelThreadSendWaypointToEntrance()
        fun addMarkerEntranceToMap(messageSelectedEntrance: CoordinateEntrance)
        fun removePolyline()
    }

    interface ViewModel<V : MvvmView?> : MvvmViewModel<V> {
        val currentLocation: LatLng?

        @get:Bindable
        val contacts: Collection<FusedContact?>?
        fun onBottomSheetLongClick()
        fun onBottomSheetClick()
        fun onMenuCenterDeviceClicked()
        fun onClearWaypointEntranceClicked()
        fun hasLocation(): Boolean
        fun onMapReady()
        val mapLocationSource: LocationSource?
        val onMapClickListener: OnMapClickListener?
        val onMarkerClickListener: OnMarkerClickListener?
        val bottomSheetHidden: LiveData<Boolean?>?
        val center: LiveData<LatLng?>?
        val waypoint: LiveData<MessageWaypointToEntrance?>?
        fun sendLocation()
    }
}