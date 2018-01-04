package com.starks.foodspots;

import android.app.Application;

import com.starks.foodspots.utils.PrefManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sharda on 01/01/18.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public PrefManager prefManager;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        mInstance = this;

        prefManager = new PrefManager(this);

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}
