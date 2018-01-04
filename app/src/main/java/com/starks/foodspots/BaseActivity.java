package com.starks.foodspots;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.starks.foodspots.interfaces.BaseViewAction;
import com.starks.foodspots.utils.CustomLoader;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sharda on 04/01/18.
 */

public class BaseActivity extends Activity implements BaseViewAction {

    CustomLoader progress;

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoader() {
        progress = new CustomLoader(this);
        progress.showLoader();
    }

    @Override
    public void hideLoader() {
        progress.hideLoader();
    }

    @Override
    public void showNetworkTimeoutError() {
        displayMessage("Network Error: Request Timed out.");
    }

    @Override
    public void showNoNetworkException() {
        displayMessage("Network Error: No Internet access.");
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
