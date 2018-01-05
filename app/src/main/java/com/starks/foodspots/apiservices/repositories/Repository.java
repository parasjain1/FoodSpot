package com.starks.foodspots.apiservices.repositories;

import android.content.Context;
import android.util.Log;

import com.starks.foodspots.MyApplication;
import com.starks.foodspots.apiservices.APIEndpoint;
import com.starks.foodspots.apiservices.ApiClient;
import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sharda on 02/01/18.
 */

public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    String header;


    public Repository(){
        Log.d(TAG, "Header: " + header);
//        this.header = application.prefManager.getToken();
    }

    APIEndpoint apiService = ApiClient.getClient(header).create(APIEndpoint.class);

    public void signUp(Map<String, String> map, Callback callback){
        Log.d(TAG, "Signup: " + map.toString());
        Call<User> call = apiService.signUp(getPartMap(map));
        call.enqueue(callback);
    }

    public void login(Map<String, String> map, Callback callback){
        Log.d(TAG, "Login: " + map.toString());
        Call<LoginResponse> call = apiService.login(map);
        call.enqueue(callback);
    }

    public void getFoodSpots(Map<String, String> map, Callback callback){
        Log.d(TAG, "GetFoodSpots: " + map.toString());
        Call<FoodSpot[]> call = apiService.getFoodSpots(map);
        call.enqueue(callback);
    }

    public void getFoodSpot(String foodSpotId, Callback callback){
        Log.d(TAG, "GetFoodSpot: " + foodSpotId);
        Call<FoodSpot> call = apiService.getFoodSpot(foodSpotId);
        call.enqueue(callback);
    }

    public void createFoodSpot(Map<String, String> map, Callback callback){
        Log.d(TAG, "CreateFoodSpot: " + map.toString());
        Call<FoodSpot> call = apiService.createFoodSpot(getPartMap(map));
        call.enqueue(callback);
    }

    public void editFoodSpot(String foodSpotId, Map<String, String> map, Callback callback){
        Log.d(TAG, "EditFoodSpots: " + map.toString());
        Call<FoodSpot> call = apiService.editFoodSpot(foodSpotId, getPartMap(map));
        call.enqueue(callback);
    }

    public void getUser(String userId, Callback callback){
        Log.d(TAG, "GetUser: " + userId);
        Call<User> call = apiService.getUser(userId);
        call.enqueue(callback);
    }

    public void getUsers(Map<String, String> map, Callback callback){
        Log.d(TAG, "GetUsers: " + map.toString());
        Call<User[]> call = apiService.getUsers(map);
        call.enqueue(callback);
    }

    public void editProfile(String userId, Map<String, String> map, Callback callback){
        Log.d(TAG, "GetUser: " + userId);
        apiService.editUser(userId, getPartMap(map)).enqueue(callback);
    }

    public void addVote(Map<String, String> map, Callback callback){
        Log.d(TAG, "AddVote: " + map.toString());
        apiService.addVote(map).enqueue(callback);
    }

    public void editVote(String voteId, Map<String, String> map, Callback callback){
        Log.d(TAG, "EditVote: " + voteId + " \n" + map.toString());
        apiService.editVote(voteId, map).enqueue(callback);
    }

    public void deleteVote(String voteId, Callback callback){
        Log.d(TAG, "DeleteVote: " + voteId);
        apiService.deleteVote(voteId).enqueue(callback);
    }

    public void addComment(Map<String, String> map, Callback callback){
        Log.d(TAG, "AddComment: " + map.toString());
        apiService.addComment(map).enqueue(callback);
    }

    public void editComment(String voteId, Map<String, String> map, Callback callback){
        Log.d(TAG, "EditComment: " + voteId + " \n" + map.toString());
        apiService.editVote(voteId, map).enqueue(callback);
    }

    public void deleteComment(String voteId, Callback callback){
        Log.d(TAG, "DeleteComment: " + voteId );
        apiService.deleteVote(voteId).enqueue(callback);
    }

    public void getFoodSpotsForTravel(Map<String, String> map, Callback callback){
        Log.d(TAG, "FoodSpotsForTravel: " + map.toString());
        apiService.foodSpotsTravelling(map).enqueue(callback);
    }

    private Map<String, RequestBody> getPartMap(Map<String, String> map){
        Map<String, RequestBody> newMap = new HashMap<>();
        //create request body map for Multipart Request
        for(String key : map.keySet())
            newMap.put(key, map.get(key) != null ? createPartFromString(map.get(key)) : createPartFromString(""));
        return newMap;
    }

    private RequestBody createPartFromString(String text){
        return  RequestBody.create(
                MediaType.parse("multipart/form-data"),  text );

    }
}
