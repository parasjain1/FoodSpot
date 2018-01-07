package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.interfaces.OnAddFoodSpotListener;
import com.starks.foodspots.models.FoodSpot;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by monikapandey on 07/01/18.
 */

public class AddFoodSpotPresenter {
    private static final String TAG = AddFoodSpotPresenter.class.getSimpleName();
    OnAddFoodSpotListener onAddFoodSpotListener;
    Repository repository = new Repository();
    public AddFoodSpotPresenter(OnAddFoodSpotListener onAddFoodSpotListener){
        this.onAddFoodSpotListener = onAddFoodSpotListener;
    }

    public void addFoodSpot(MultipartBody.Part[] images, Map<String, String> map){
        onAddFoodSpotListener.showLoader();
        repository.createFoodSpot(images, map, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                onAddFoodSpotListener.hideLoader();
                if(response.code() == 201){ //created
                    onAddFoodSpotListener.onFoodSpotAdded((FoodSpot) response.body());
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
                onAddFoodSpotListener.hideLoader();
            }
        });
    }
}
