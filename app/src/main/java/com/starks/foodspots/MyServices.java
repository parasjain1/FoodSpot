package com.starks.foodspots;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by Shashank on 02-Jan-18.
 */

public class MyServices extends Service {
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
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                //sendRequestToServer();   //Your code here
//                Toast.makeText(getBaseContext(), "hello", Toast.LENGTH_LONG).show();
//            }
//        }, 0, 5*60*1000);//5 Minutes

        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    if(appContext != null)
                        Toast.makeText(appContext, "hello", Toast.LENGTH_LONG).show();

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


