package com.starks.foodspots;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.interfaces.LoginViewAction;
import com.starks.foodspots.utils.PrefManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends Activity implements LoginViewAction{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void hideLoader() {

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
    public void onLogin(LoginResponse loginResponse) {
        new PrefManager(this).putToken(loginResponse.getToken());
    }
}
