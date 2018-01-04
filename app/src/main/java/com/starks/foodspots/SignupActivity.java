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
import com.starks.foodspots.presenters.LoginPresenter;
import com.starks.foodspots.presenters.SignupPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SignupActivity extends BaseActivity implements SignupViewAction, LoginViewAction{

    TextView loginButton;
    EditText nameEditText,emailEditText,passwordEditText,ageEditText;
    RadioButton maleRadio,femaleRadio;
    Button signUpButton;
    SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new SignupPresenter(this);
        initViews();

    }


    void initViews(){
        loginButton = (TextView) findViewById(R.id.btnLinkLogin);
        nameEditText=(EditText) findViewById(R.id.signupInputName);
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
                if(validated()){
                    presenter.signup(getFormData());
                } else {
                    displayMessage("Correct the errors!");
                }
            }
        });
    }



    @Override
    public void onSignUp(User user) {
        LoginPresenter loginPresenter = new LoginPresenter(this);
        Map<String,String> map = new HashMap<>();
        map.put("username", emailEditText.getText().toString());
        map.put("password", passwordEditText.getText().toString());
        loginPresenter.login(map);
    }


    @Override
    public void onLogin(LoginResponse loginResponse) {
        MyApplication.getInstance().prefManager.putToken(loginResponse.getToken());
        startActivity(new Intent(this, MainActivity.class));
    }

    private boolean validated(){
        if(
                nameEditText.getText().toString().equals("") ||
                emailEditText.getText().toString().equals("") ||
                passwordEditText.getText().toString().equals("")
        ) return false;

        return  true;
    }

    private Map<String, String> getFormData(){
        Map<String,String> map = new HashMap<>();
        map.put("fullName", nameEditText.getText().toString());
        map.put("username", emailEditText.getText().toString());
        map.put("password", passwordEditText.getText().toString());
        map.put("email", emailEditText.getText().toString());
        return map;
    }
}
