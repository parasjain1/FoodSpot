package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.interfaces.SignupViewAction;
import com.starks.foodspots.models.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 02/01/18.
 */

public class SignupPresenter {

    private static final String TAG = SignupPresenter.class.getSimpleName();
    SignupViewAction viewAction;
    Repository repository = new Repository();

    public SignupPresenter(SignupViewAction signupViewAction){
        this.viewAction = signupViewAction;
    }

    public void signup(Map<String, String> data){
        viewAction.showLoader();
        repository.signUp(data, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                viewAction.hideLoader();
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 201){
                    viewAction.onSignUp((User) response.body());
                } else {
                    viewAction.displayMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                viewAction.hideLoader();
                Log.d(TAG, t.getMessage());
            }
        });

    }
}
