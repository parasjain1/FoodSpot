package com.starks.foodspots;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.interfaces.GetUsersListener;
import com.starks.foodspots.interfaces.LoginViewAction;
import com.starks.foodspots.models.User;
import com.starks.foodspots.presenters.GetUsersPresenter;
import com.starks.foodspots.presenters.LoginPresenter;
import com.starks.foodspots.utils.CustomLoader;
import com.starks.foodspots.utils.PrefManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.message;

public class LoginActivity extends BaseActivity implements LoginViewAction {

    TextView registerTextView;
    EditText emailEditText,passwordEditText;
    Button loginButton;
    LoginPresenter presenter;
    TextView slide;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkAndRequestPermissions();
        initViews();
        presenter = new LoginPresenter(this);
    }

    void initViews() {
        registerTextView = (TextView) findViewById(R.id.registerButton);
        loginButton=(Button) findViewById(R.id.loginButton);
        emailEditText=(EditText) findViewById(R.id.email);
        passwordEditText=(EditText) findViewById(R.id.password);
        slide=(TextView) findViewById(R.id.slide);
        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),CreateFoodspotActivity.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(validated()){
                    presenter.login(getFormData());
                } else {
                    displayMessage("Please provide correct username and password!");
                }

            }
        });


        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });
    }

    Map<String, String> getFormData(){
        Map<String, String > map = new HashMap<>();
        map.put("username", emailEditText.getText().toString());
        map.put("password", passwordEditText.getText().toString());
        return map;
    }

    Boolean validated(){
        if(passwordEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("")) {
            return false;
        } else return true;
    }


    @Override
    public void onLogin(LoginResponse loginResponse) {
        MyApplication.getInstance().prefManager.putToken(loginResponse.getToken());
        new GetUsersPresenter(new GetUsersListener() {
            @Override
            public void onReceiveUsers(ArrayList<User> users) {

            }

            @Override
            public void onReceiveUser(User user) {
                MyApplication.getInstance().prefManager.putUser(user);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void displayMessage(String message) {

            }

            @Override
            public void showLoader() {

            }

            @Override
            public void hideLoader() {

            }

            @Override
            public void showNetworkTimeoutError() {

            }

            @Override
            public void showNoNetworkException() {

            }
        }).getCurrentUser();

    }

    private  boolean checkAndRequestPermissions() {
//        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
//                android.Manifest.permission.SEND_SMS);

        int locationPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int locationPermissionCoarse = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int writePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if(locationPermissionCoarse != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if(writePermission != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(readPermission != PackageManager.PERMISSION_GRANTED)
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }

        //        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
        //            listPermissionsNeeded.add(android.Manifest.permission.SEND_SMS);
        //        }

        return true;
    }
}
