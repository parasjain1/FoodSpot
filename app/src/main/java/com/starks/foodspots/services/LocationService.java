package com.starks.foodspots.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

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

public class LocationService extends Service {
    private static final String TAG =  "LocationService";
    private Timer mTimer;
    Context appContext;
    private Timer timer = new Timer();


    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        appContext=getBaseContext();//Get the context here
        return  super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate()
    {
        super.onCreate();

        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    if(appContext != null){
                        Repository repository = new Repository();
                        Map<String, String> params = new HashMap<>();
                        params.put("lat", "28.7041");
                        params.put("lat", "77.1025");
                        repository.getFoodSpots(params, new Callback() {
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


                } finally {
                    handler.postDelayed(this,5000);
                }

            }
        };
        handler.postDelayed(runnable,5000);
        runnable.run();


    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

}


