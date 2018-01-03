package com.starks.foodspots;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.interfaces.LoginViewAction;
import com.starks.foodspots.utils.PrefManager;

import static android.R.id.message;

public class LoginActivity extends Activity implements LoginViewAction {

    TextView registerTextView;
    EditText emailEditText,passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    void initViews() {
        registerTextView = (TextView) findViewById(R.id.registerButton);
        loginButton=(Button) findViewById(R.id.loginButton);
        emailEditText=(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
                setContentView(R.layout.activity_signup);
            }
        });
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
