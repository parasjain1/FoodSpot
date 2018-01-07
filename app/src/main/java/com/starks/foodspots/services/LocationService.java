package com.starks.foodspots.services;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.iid.FirebaseInstanceId;
import com.starks.foodspots.MyApplication;
import com.starks.foodspots.apiservices.repositories.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shashank on 02-Jan-18.
 */

public class LocationService extends Service implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private static final String TAG = LocationService.class.getSimpleName();
    private static final Integer REQUEST_INTERVAL = 30000;
    Context appContext;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 2000; // 2 sec
    private static int FATEST_INTERVAL = 1000; // 1 sec
    private static int DISPLACEMENT = 10; // 10 meters

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private static final long SPLASH_TIME_OUT = 5;
    public final static String EXTRA_MESSAGE = "null";

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;


    public static double latitude;
    public static double longitude;

    private static LocationService mInstance;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        appContext = getBaseContext();//Get the context here
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate() {

        mInstance = this;

        super.onCreate();


        if (ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocationService.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d(TAG, "Location permissions disabled.");
                return;
        }

        buildGoogleApiClient();
        createLocationRequest();


        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                        sendTravelRequest(getLocation(LocationService.this));
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                } finally {
                    handler.postDelayed(this,REQUEST_INTERVAL);
                }

            }
        };
        handler.postDelayed(runnable,5000);
//        runnable.run();

    }


    void sendTravelRequest(Location location){

        if(location == null){
            Log.d(TAG, "Location is null!");
            return;
        }

        String firebaseTokenId = FirebaseInstanceId.getInstance().getToken();
        if(firebaseTokenId == null){
            Log.d(TAG, "FirebaseTokenId is null");
            return;
        }

        Log.d(TAG, "sending request...");
        Repository repository = new Repository();
        Map<String, String> params = new HashMap<>();
        params.put("lat", location.getLatitude() + "");
        params.put("lng", location.getLongitude() + "");
        params.put("firebaseId", firebaseTokenId);

        repository.getFoodSpotsForTravel(params, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + ", " + response.toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "Network Request Failed: " + t.getMessage());
            }
        });

    }

    @Override
    public void onLocationChanged(Location location) {

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // Once connected with google api, get the location
        //displayLocation();

            startLocationUpdates();


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    protected void startLocationUpdates() throws SecurityException {

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    private Location getLocation(Context context) throws SecurityException {

        return LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

    }

    public static synchronized LocationService getInstance() {
        return mInstance;
    }


}


