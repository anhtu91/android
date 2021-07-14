package org.owntracks.android.ui.map;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.owntracks.android.R;
import org.owntracks.android.data.repos.LocationRepo;
import org.owntracks.android.databinding.UiMapBinding;
import org.owntracks.android.model.CoordinateEntrance;
import org.owntracks.android.model.FusedContact;
import org.owntracks.android.model.messages.MessageTransmittSelectedEntrance;
import org.owntracks.android.model.messages.MessageWaypointToEntrance;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.services.LocationProcessor;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.services.MessageProcessorEndpointHttp;
import org.owntracks.android.services.worker.Scheduler;
import org.owntracks.android.support.ContactImageProvider;
import org.owntracks.android.support.Events;
import org.owntracks.android.support.GeocodingProvider;
import org.owntracks.android.support.RunThingsOnOtherThreads;
import org.owntracks.android.support.widgets.BindingConversions;
import org.owntracks.android.ui.base.BaseActivity;
import org.owntracks.android.ui.welcome.WelcomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import timber.log.Timber;

public class MapActivity extends BaseActivity<UiMapBinding, MapMvvm.ViewModel> implements MapMvvm.View, View.OnClickListener, View.OnLongClickListener, PopupMenu.OnMenuItemClickListener, OnMapReadyCallback, Observer {
    private static final long ZOOM_LEVEL_STREET = 15;
    private final int PERMISSIONS_REQUEST_CODE = 1;

    private final Map<String, Marker> markers = new HashMap<>();
    private GoogleMap googleMap;
    private BottomSheetBehavior<LinearLayout> bottomSheetBehavior;
    private boolean isMapReady = false;
    private Menu mMenu;

    private Polyline currentPolyline;
    private volatile boolean threadSendWaypointToEntranceFlag = false;

    private String lastKeyIDSelectedEntrance = null;
    private String lastFieldNameSelectedEntrance = null;
    private double latitudeSelectedEntrance = 0;
    private double longitudeSelectedEntrance = 0;
    private boolean sendEntranceMessage = false;

    @Inject
    MessageProcessor messageProcessor;

    private FusedLocationProviderClient fusedLocationClient;
    LocationCallback locationRepoUpdaterCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Timber.d("Foreground location result received: %s", locationResult);
            locationRepo.setCurrentLocation(locationResult.getLastLocation());
            super.onLocationResult(locationResult);
        }
    };

    @Inject
    LocationRepo locationRepo;

    @Inject
    RunThingsOnOtherThreads runThingsOnOtherThreads;

    @Inject
    ContactImageProvider contactImageProvider;

    @Inject
    EventBus eventBus;

    @Inject
    LocationProcessor locationProcessor;

    @Inject
    Scheduler scheduler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!preferences.isSetupCompleted()) {
            navigator.startActivity(WelcomeActivity.class);
            finish();
        }

        bindAndAttachContentView(R.layout.ui_map, savedInstanceState);

        setSupportToolbar(this.binding.toolbar, false, true);
        setDrawer(this.binding.toolbar);

        // Workaround for Google Maps crash on Android 6
        try {
            binding.mapView.onCreate(savedInstanceState);
        } catch (Exception e) {
            Timber.e(e, "Failed to bind map to view.");
            isMapReady = false;
        }
        this.bottomSheetBehavior = BottomSheetBehavior.from(this.binding.bottomSheetLayout);
        this.binding.moreButton.setOnClickListener(this::showPopupMenu);
        setBottomSheetHidden();

        AppBarLayout appBarLayout = this.binding.appBarLayout;
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);

        viewModel.getWaypoint().observe(this, this);
        viewModel.getBottomSheetHidden().observe(this, o -> {
            if ((Boolean) o) {
                setBottomSheetHidden();
            } else {
                setBottomSheetCollapsed();
            }
        });
        viewModel.getCenter().observe(this, o -> {
            if (o != null) {
                updateCamera((LatLng) o);
            }
        });
        checkAndRequestLocationPermissions();
        Timber.v("starting BackgroundService");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService((new Intent(this, BackgroundService.class)));
        } else {
            startService((new Intent(this, BackgroundService.class)));
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(currentPolyline != null){
            currentPolyline.remove();
        }
    }

    private void checkAndRequestLocationPermissions() {
        if (!requirementsChecker.isPermissionCheckPassed()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Activity currentActivity = this;
                    new AlertDialog.Builder(this)
                            .setCancelable(true)
                            .setMessage(R.string.permissions_description)
                            .setPositiveButton("OK", (dialog, which) ->
                                    ActivityCompat.requestPermissions(currentActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE)
                            )
                            .show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE);
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onChanged(@Nullable Object messageWaypointToEntrance) { //This displays and set name for bottomsheet
        if (messageWaypointToEntrance != null) {
            Timber.i("Receive string "+messageWaypointToEntrance.toString());

            MessageWaypointToEntrance waypointToEntrance = (MessageWaypointToEntrance) messageWaypointToEntrance;
            binding.distance.setText(waypointToEntrance.getDistance().toString());
            binding.duration.setText(waypointToEntrance.getDuration().toString());
            binding.keyIDSelectedEntrance.setText(waypointToEntrance.getSelectedKeyIDEntrance());
            binding.fieldNameSelectedEntrance.setText(waypointToEntrance.getSelectedFieldNameEntrance());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            binding.mapView.onSaveInstanceState(bundle);
        } catch (Exception ignored) {
            isMapReady = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        try {
            binding.mapView.onDestroy();
        } catch (Exception ignored) {
            isMapReady = false;
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.isMapReady = false;

        try {
            binding.mapView.onResume();

            if (googleMap == null) {
                Timber.v("map not ready. Running initDelayed()");
                this.isMapReady = false;
                initMapDelayed();
            } else {
                Timber.v("map ready. Running onMapReady()");
                this.isMapReady = true;
                viewModel.onMapReady();
            }
        } catch (EventBusException e) {
            Timber.e(e, "EventBus Exception");
        } catch (Exception e){
            Timber.e(e, "Not showing map due to crash in Google Maps library");
            isMapReady = false;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            checkAndRequestLocationPermissions();
        }

        fusedLocationClient.requestLocationUpdates(
                new LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(TimeUnit.SECONDS.toMillis(5)),
                locationRepoUpdaterCallback,
                null
        ).addOnCompleteListener(task ->
                Timber.i("Requested foreground location updates. isSuccessful: %s isCancelled: %s", task.isSuccessful(), task.isCanceled())
        );
        //updateMonitoringModeMenu();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            binding.mapView.onPause();
        } catch (Exception e) {
            isMapReady = false;
        }
        fusedLocationClient.removeLocationUpdates(locationRepoUpdaterCallback).addOnCompleteListener(task ->
                Timber.i("Removed foreground location updates. isSuccessful: %s isCancelled: %s", task.isSuccessful(), task.isCanceled())
        );
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        try {
            binding.mapView.onLowMemory();
        } catch (Exception ignored) {
            isMapReady = false;
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            binding.mapView.onLowMemory();
        } catch (Exception ignored) {
            isMapReady = false;
        }
    }

    private void initMapDelayed() {
        isMapReady = false;
        runThingsOnOtherThreads.postOnMainHandlerDelayed(this::initMap, 500);
    }

    private void initMap() {
        isMapReady = false;
        try {
            binding.mapView.getMapAsync(this);
        } catch (Exception ignored) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_map, menu);
        this.mMenu = menu;
        if (viewModel.hasLocation())
            enableLocationMenus();
        else
            disableLocationMenus();

        updateMonitoringModeMenu();

        return true;
    }

    public void updateMonitoringModeMenu() {
        if (this.mMenu == null) {
            return;
        }
        MenuItem item = this.mMenu.findItem(R.id.menu_monitoring);

        switch (preferences.getMonitoring()) {
            case LocationProcessor.MONITORING_QUIET:
                item.setIcon(R.drawable.ic_baseline_stop_36);
                item.setTitle(R.string.monitoring_quiet);
                sendEntranceMessage = false;
                cancelThreadSendWaypointToEntrance(); //Stop sending MQTT message to get waypoint to entrance of parking spot when current parking spot full
                break;
            case LocationProcessor.MONITORING_MANUAL:
                item.setIcon(R.drawable.ic_baseline_pause_36);
                item.setTitle(R.string.monitoring_manual);
                sendEntranceMessage = false;
                cancelThreadSendWaypointToEntrance(); //Stop sending MQTT message to get waypoint to entrance of parking spot when current parking spot full
                break;
            case LocationProcessor.MONITORING_SIGNIFICANT:
                item.setIcon(R.drawable.ic_baseline_play_arrow_36);
                item.setTitle(R.string.monitoring_significant);
                sendEntranceMessage = false;
                break;
            case LocationProcessor.MONITORING_MOVE:
                item.setIcon(R.drawable.ic_step_forward_2);
                item.setTitle(R.string.monitoring_move);
                sendEntranceMessage = true;
                threadSendWaypointToEntranceFlag = true;
                runThreadSelectedEntrance(); //Run thread again if a entrance is selected
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_report) {
            viewModel.sendLocation();
            //Send MQTT message to get waypoint to entrance
            sendEntranceMessage = true;
            threadSendWaypointToEntranceFlag = true;
            sendSelectedEtrance();
            return true;
        } else if (itemId == R.id.menu_mylocation) {
            viewModel.onMenuCenterDeviceClicked();
            return true;
        } else if (itemId == android.R.id.home) {
            finish();
            return true;
        } else if (itemId == R.id.menu_monitoring) {
            stepMonitoringModeMenu();
        }
        return false;
    }

    private void stepMonitoringModeMenu() {
        preferences.setMonitoringNext();

        int newmode = preferences.getMonitoring();
        if (newmode == LocationProcessor.MONITORING_QUIET) {
            Toast.makeText(this, R.string.monitoring_quiet, Toast.LENGTH_SHORT).show();
        } else if (newmode == LocationProcessor.MONITORING_MANUAL) {
            Toast.makeText(this, R.string.monitoring_manual, Toast.LENGTH_SHORT).show();
        } else if (newmode == LocationProcessor.MONITORING_SIGNIFICANT) {
            Toast.makeText(this, R.string.monitoring_significant, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.monitoring_move, Toast.LENGTH_SHORT).show();
        }
    }

    private void disableLocationMenus() {
        if (this.mMenu != null) {
            this.mMenu.findItem(R.id.menu_mylocation).setEnabled(false).getIcon().setAlpha(128);
            this.mMenu.findItem(R.id.menu_report).setEnabled(false).getIcon().setAlpha(128);
        }
    }

    public void enableLocationMenus() {
        if (this.mMenu != null) {
            this.mMenu.findItem(R.id.menu_mylocation).setEnabled(true).getIcon().setAlpha(255);
            this.mMenu.findItem(R.id.menu_report).setEnabled(true).getIcon().setAlpha(255);
        }
    }

    // MAP CALLBACKS
    @SuppressWarnings("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setIndoorEnabled(false);
        this.googleMap.setLocationSource(viewModel.getMapLocationSource());
        this.googleMap.setMyLocationEnabled(true);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        this.googleMap.setOnMapClickListener(viewModel.getOnMapClickListener());
        this.googleMap.setOnMarkerClickListener(viewModel.getOnMarkerClickListener());
        this.googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        this.isMapReady = true;
        viewModel.onMapReady();

        sendEntranceMessage = false;
        threadSendWaypointToEntranceFlag = false;
        removePolyline();
    }


    private void updateCamera(@NonNull LatLng latLng) {
        if (isMapReady)
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_LEVEL_STREET));
    }

    @Override
    public void removePolyline() {
        if(currentPolyline != null){
            currentPolyline.remove();
        }
    }

    @Override
    public void clearMarkers() {
        if (isMapReady) {
            googleMap.clear();
        }
        markers.clear();
    }

    @Override
    public void removeMarker() {
        if(markers.containsKey(getString(R.string.markerParkingEntrance))) {
            Marker m = markers.get(getString(R.string.markerParkingEntrance));
            if (m != null)
                m.remove();
        }
    }

    @Override
    public void updateMarker(@Nullable FusedContact contact) {
        if (contact == null || !contact.hasLocation() || !isMapReady) {
            Timber.v("unable to update marker. null:%s, location:%s, mapReady:%s", contact == null, contact == null || contact.hasLocation(), isMapReady);
            return;
        }

        Timber.v("updating marker for contact: %s", contact.getId());
        Marker marker = markers.get(contact.getId());

        if (marker != null && marker.getTag() != null) {
            marker.setPosition(contact.getLatLng());
        } else {
            // If a marker has been removed, its tag will be null. Doing anything with it will make it explode
            if (marker != null) {
               markers.remove(contact.getId());
            }
            marker = googleMap.addMarker(new MarkerOptions().position(contact.getLatLng()).anchor(0.5f, 0.5f).visible(false));
            marker.setTag(contact.getId());
            markers.put(contact.getId(), marker);
        }

        contactImageProvider.setMarkerAsync(marker, contact);
    }

    public void addMarkerEntranceToMap(CoordinateEntrance messageSelectedEntrance){
        Marker recommendParkingSpot = googleMap.addMarker(new MarkerOptions().position(new LatLng(messageSelectedEntrance.getLangtitude(), messageSelectedEntrance.getLongitude())));
        recommendParkingSpot.setTag(getString(R.string.markerParkingEntrance));

        removeMarker();

        markers.put(getString(R.string.markerParkingEntrance), recommendParkingSpot);

        sendEntranceMessage = true;
        lastKeyIDSelectedEntrance = messageSelectedEntrance.getKeyIDEntrance();
        lastFieldNameSelectedEntrance = messageSelectedEntrance.getFieldNameEntrance();
        latitudeSelectedEntrance = messageSelectedEntrance.getLangtitude();
        longitudeSelectedEntrance = messageSelectedEntrance.getLongitude();

        threadSendWaypointToEntranceFlag = true;
        runThreadSelectedEntrance();
    }

    public void runThreadSelectedEntrance(){

        Thread threadSendWaypointToEntrance = new Thread(){
            @Override
            public void run() {
                try{
                    while(threadSendWaypointToEntranceFlag){
                        sendSelectedEtrance();
                        sleep(500);
                    }
                }catch (Exception e){
                    Timber.e("Error while running thread to send selected entrance "+e.toString());
                }
            }
        };

        threadSendWaypointToEntrance.start();
    }

    public void cancelThreadSendWaypointToEntrance(){
        threadSendWaypointToEntranceFlag = false;
    }

    private void sendSelectedEtrance(){  //Send MQTT message to NodeRED to get waypoints from OSRM
        if(sendEntranceMessage && lastKeyIDSelectedEntrance != null && lastFieldNameSelectedEntrance != null && latitudeSelectedEntrance != 0 && longitudeSelectedEntrance != 0){
            MessageTransmittSelectedEntrance message = new MessageTransmittSelectedEntrance();
            message.setSelectedKeyIDEntrance(lastKeyIDSelectedEntrance);
            message.setSelectedFieldNameEntrance(lastFieldNameSelectedEntrance);
            message.setLatitudeSelectedEntrance(latitudeSelectedEntrance);
            message.setLongitudeSelectedEntrance(longitudeSelectedEntrance);
            message.setCurrentLatitude(viewModel.getCurrentLocation().latitude);
            message.setCurrentLongitude(viewModel.getCurrentLocation().longitude);
            messageProcessor.queueMessageForSending(message);
        }
    }

    @Override
    public void updateWaypointToEntrance(@NotNull MessageWaypointToEntrance messageWaypointToEntrance) {
        if(threadSendWaypointToEntranceFlag){
            PolylineOptions polylineOptions = new PolylineOptions();
            ArrayList<LatLng> latlonWaypointToEntrance = new ArrayList<LatLng>();

            for(int i=0; i<messageWaypointToEntrance.getCoordinatesArray().size(); i++){
                latlonWaypointToEntrance.add(new LatLng(messageWaypointToEntrance.getCoordinatesArray().get(i).get(1), messageWaypointToEntrance.getCoordinatesArray().get(i).get(0)));
            }

            polylineOptions.clickable(true).color(Color.BLUE).addAll(latlonWaypointToEntrance);

            if(currentPolyline != null){
                currentPolyline.remove();
            }
            currentPolyline = googleMap.addPolyline(polylineOptions);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_navigate:
                if(latitudeSelectedEntrance != 0 && longitudeSelectedEntrance != 0){
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + latitudeSelectedEntrance + "," + longitudeSelectedEntrance));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(this, getString(R.string.noNavigationApp), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, getString(R.string.locationUnknown), Toast.LENGTH_SHORT).show();
                }

                return true;
            case R.id.menu_clear:
                viewModel.stopRequestWaypointThread();
            default:
                return false;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        viewModel.onBottomSheetLongClick();
        return true;
    }

    @Override
    public void setBottomSheetExpanded() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    // BOTTOM SHEET CALLBACKS
    @Override
    public void onClick(View view) {
        viewModel.onBottomSheetClick();
    }

    @Override
    public void setBottomSheetCollapsed() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void setBottomSheetHidden() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        if (mMenu != null)
            mMenu.close();
    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v, Gravity.START); //new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup_contacts, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        if (preferences.getMode() == MessageProcessorEndpointHttp.MODE_ID)
            popupMenu.getMenu().removeItem(R.id.menu_clear);
        popupMenu.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            eventBus.postSticky(new Events.PermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION));
        }
    }
}
