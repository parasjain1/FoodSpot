package com.starks.foodspots.presenters;

import android.util.Log;
import android.widget.Toast;

import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.interfaces.LoginViewAction;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 02/01/18.
 */

public class LoginPresenter {

    Repository repository = new Repository();
    LoginViewAction viewAction;
    private static final String TAG = LoginPresenter.class.getSimpleName();

    public LoginPresenter(LoginViewAction loginViewAction){
        this.viewAction = loginViewAction;
    }

    public void login(Map<String, String> data){
        repository.login(data, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                LoginResponse loginResponse = (LoginResponse) response.body();
                if(response.code() == 200)
                    viewAction.onLogin(loginResponse);
                else if(loginResponse != null && loginResponse.getNon_field_errors() != null)
                    viewAction.displayMessage("Invalid credentials!");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
                viewAction.displayMessage("Some problem occurred when trying to login!");
            }
        });
    }
}
