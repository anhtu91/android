package org.owntracks.android.ui.map;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.owntracks.android.data.repos.ContactsRepo;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.model.CoordinateEntrance;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageCancelRequestWaypointToSelectedEntrance;
import org.owntracks.android.model.messages.MessageLocation;
import org.owntracks.android.model.messages.MessageWaypointToEntrance;
import org.owntracks.android.services.LocationProcessor;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.support.Events;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;

import java.util.Collection;

import javax.inject.Inject;

import timber.log.Timber;

@PerActivity
public class MapViewModel extends BaseViewModel<MapMvvm.View> implements MapMvvm.ViewModel<MapMvvm.View>, LocationSource, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener{
    private final ContactsRepo contactsRepo;
    private final LocationProcessor locationProcessor;
    private LocationSource.OnLocationChangedListener onLocationChangedListener;
    private MessageProcessor messageProcessor;
    private Location location;


    private static final int VIEW_FREE = 0;
    private static final int VIEW_DEVICE = 1;


    private static int mode = VIEW_DEVICE;

    private MutableLiveData<Boolean> liveBottomSheetHidden = new MutableLiveData<>();
    private MutableLiveData<LatLng> liveCamera = new MutableLiveData<>();
    private MutableLiveData<MessageWaypointToEntrance> liveTestData = new MutableLiveData<>();

    @Inject
    public MapViewModel(ContactsRepo contactsRepo, LocationProcessor locationRepo, MessageProcessor messageProcessor) {
        Timber.v("onCreate");
        this.contactsRepo = contactsRepo;
        this.messageProcessor = messageProcessor;
        this.locationProcessor = locationRepo;
    }

    @Override
    public void saveInstanceState(@NonNull Bundle outState) {
    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {
    }

    @Override
    public LocationSource getMapLocationSource() {
        return this;
    }

    @Override
    public GoogleMap.OnMapClickListener getOnMapClickListener() {
        return this;
    }

    @Override
    public GoogleMap.OnMarkerClickListener getOnMarkerClickListener() {
        return this;
    }

    @Override
    public void onMapReady() {
        for (Object c : contactsRepo.getAllAsList()) {
            getView().updateMarker((FusedContact) c);
        }

        Timber.i("Mode onMapReady "+mode);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        if (mode == VIEW_FREE) {
            setViewModeFree();
        } else {
            setViewModeDevice();
        }
    }

    @Override
    public LiveData<Boolean> getBottomSheetHidden() {
        return liveBottomSheetHidden;
    }

    @Override
    public LiveData<LatLng> getCenter() {
        return liveCamera;
    }

    @Override
    public LiveData<MessageWaypointToEntrance> getWaypoint(){
        return liveTestData;
    }

    @Override
    public void sendLocation() {
        locationProcessor.publishLocationMessage(MessageLocation.REPORT_TYPE_USER);
    }

    private void setViewModeWaypointToEntrance(){
        setViewModeFree();
        liveBottomSheetHidden.postValue(false);
    }

    private void setViewModeFree() {
        Timber.v("setting view mode: VIEW_FREE");
        mode = VIEW_FREE;
    }

    private void setViewModeDevice() {
        Timber.v("setting view mode: VIEW_DEVICE");

        mode = VIEW_DEVICE;
        if (hasLocation()) {
            liveCamera.postValue(getCurrentLocation());
        } else {
            Timber.e("no location available");
        }
    }

    @Override
    @Nullable
    public LatLng getCurrentLocation() {
        return location != null ? new LatLng(location.getLatitude(), location.getLongitude()) : null;
    }

    @Override
    public Collection<FusedContact> getContacts() {
        return this.contactsRepo.getAllAsList();
    }

    @Override
    public boolean hasLocation() {
        return location != null;
    }

    @Override
    public void onBottomSheetClick() {
        getView().setBottomSheetExpanded();
    }

    @Override
    public void onMenuCenterDeviceClicked() {
        setViewModeDevice();
    }

    private void onClearWaypointEntranceClicked() {
        stopRequestWaypointThread();
    }

    public void stopRequestWaypointThread(){
        getView().cancelThreadSendWaypointToEntrance();
        getView().removeMarker();
        getView().removePolyline();
        sendCancelWaypointsToEntrance();
        setViewModeDevice();
    }

    private void sendCancelWaypointsToEntrance(){ //When user clicked cancel or when user is right in the selected entrance => Send message to update status sendWaypoints of user in database server
        MessageCancelRequestWaypointToSelectedEntrance message = new MessageCancelRequestWaypointToSelectedEntrance();
        message.setCancelWaypointToEntrance(true);
        messageProcessor.queueMessageForSending(message);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.FusedContactAdded e) {
        onEvent(e.getContact());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FusedContact c) {
        getView().updateMarker(c);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.ModeChanged e) {
        getView().clearMarkers();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Events.MonitoringChanged e) {
        getView().updateMonitoringModeMenu();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1, sticky = true)
    public void onEvent(@NonNull Location location) {
        Timber.v("location source updated");
        this.location = location;
        getView().enableLocationMenus();
        if (mode == VIEW_DEVICE) {
            liveCamera.postValue(getCurrentLocation());
        }
        if (onLocationChangedListener != null) {
            this.onLocationChangedListener.onLocationChanged(this.location);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoordinateEntrance messageSelectedEntrance) { //For case: parking spot is full and recommend new parking spot around. Receive Info of selected entrance from UI
        Timber.i("Coordinate Entrance " + messageSelectedEntrance.toString());
        getView().addMarkerEntranceToMap(messageSelectedEntrance);
        setViewModeFree();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageWaypointToEntrance messageWaypointToEntrance) { //Receive waypoint from NodeRED
        Timber.i("Message To Entrance "+messageWaypointToEntrance.getDuration());
        if(messageWaypointToEntrance.getDistance() != 0){ //If distance != 0 => keep sending waypoints request to server
            getView().updateWaypointToEntrance(messageWaypointToEntrance);
            liveTestData.postValue(messageWaypointToEntrance);
        }else{ //If distance = 0 => user is right in selected entrance => remove waypoint, marker and stop sending waypoints request to server
            onClearWaypointEntranceClicked();
        }
    }

    // Map Callback
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        Timber.v("location source activated");
        this.onLocationChangedListener = onLocationChangedListener;
        if (location != null)
            this.onLocationChangedListener.onLocationChanged(location);
    }

    // Map Callback
    @Override
    public void deactivate() {
        onLocationChangedListener = null;
    }

    // Map Callback
    @Override
    public void onMapClick(LatLng latLng) {
        setViewModeFree();
    }

    // Map Callback
    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.getTag().toString().equals("Selected entrance")){
            setViewModeWaypointToEntrance();
        }

        return true;
    }

    @Override
    public void onBottomSheetLongClick() {
        setViewModeWaypointToEntrance();
    }
}
