package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.interfaces.FoodSpotViewAction;
import com.starks.foodspots.models.FoodSpot;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by monikapandey on 04/01/18.
 */

public class FoodSpotsPresenter {

    private Repository repository = new Repository();
    private FoodSpotViewAction viewAction;
    private static final String TAG = FoodSpotsPresenter.class.getSimpleName();

    public FoodSpotsPresenter(FoodSpotViewAction foodSpotViewAction){
        this.viewAction = foodSpotViewAction;
    }

    public void getFoodSpots(Map<String, String> map){
        repository.getFoodSpots(map, new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 200)
                    viewAction.onFoodSpotsReceived((ArrayList<FoodSpot>) response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
                viewAction.displayMessage("Some error occurred while getting foodspots!");
            }

        });
    }




}
