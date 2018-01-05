package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.interfaces.GetUsersListener;
import com.starks.foodspots.models.User;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 05/01/18.
 */

public class GetUsersPresenter {

    private static final String TAG = GetUsersPresenter.class.getSimpleName();
    GetUsersListener viewAction;
    Repository repository = new Repository();

    public GetUsersPresenter(GetUsersListener getUsersListener){
        this.viewAction = getUsersListener;
    }

    public void getUsers(Map<String, String> map){
        repository.getUsers(map, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 200){
                    ArrayList<User> users = new ArrayList<User>();
                    for(User user : (User[]) response.body())
                        users.add(user);
                    viewAction.onReceiveUsers(users);
                } else {
                    viewAction.displayMessage(response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
                viewAction.displayMessage("Something went wrong while getting users data!");
            }
        });
    }

    public void getUser(Integer userId){
        repository.getUser(userId.toString(), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 200){

                    viewAction.onReceiveUser((User) response.body());
                } else {
                    viewAction.displayMessage(response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
                viewAction.displayMessage("Something went wrong while getting users data!");
            }
        });
    }
}
