package com.starks.foodspots;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;


public class SplashActivity extends BaseActivity {
    private static int  SPLASH_TIME_OUT=3000;
    private static String TAG = SplashActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        startService(new Intent(this, LocationService.class)); //don't start here, first take necessary permissions from user
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Class targetClass = (
                        MyApplication
                                .getInstance()
                                .prefManager
                                .getToken()
                                .equals("")
                ) ? LoginActivity.class : MainActivity.class;
                Intent i=new Intent(SplashActivity.this, targetClass);
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);


    }

}
