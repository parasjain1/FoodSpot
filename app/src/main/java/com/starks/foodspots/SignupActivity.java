package com.starks.foodspots;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Downloader;
import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.interfaces.LoginViewAction;
import com.starks.foodspots.interfaces.SignupViewAction;
import com.starks.foodspots.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SignupActivity extends AppCompatActivity implements SignupViewAction{

    TextView loginButton;
    EditText nameEditText,emailEditText,passwordEditText,ageEditText;
    RadioButton maleRadio,femaleRadio;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

    }


    void initViews(){
        loginButton = (TextView) findViewById(R.id.btnLinkLogin);
        nameEditText=(EditText) findViewById(R.id.);
        emailEditText=(EditText) findViewById(R.id.signupInputEmail);
        passwordEditText=(EditText) findViewById(R.id.signupInputPassword);
        ageEditText=(EditText) findViewById(R.id.signupInputAge);
        maleRadio=(RadioButton) findViewById(R.id.maleRadioBtn);
        femaleRadio=(RadioButton) findViewById(R.id.femaleRadioBtn);
        signUpButton=(Button) findViewById(R.id.btnSignup);

        //goto login screen
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                setContentView(R.layout.activity_login);
            }
        });

        //submit signup request
        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
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
        displayMessage("Network Error: Request Timed out.");
    }

    @Override
    public void showNoNetworkException() {
        displayMessage("Network Error: No Internet access.");
    }


    @Override
    public void onSignup(User user) {

    }
}
