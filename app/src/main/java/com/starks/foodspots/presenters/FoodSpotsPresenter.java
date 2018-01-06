package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.FoodSpotSuggestion;
import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.interfaces.OnFoodSpotsReceiveListener;
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
    private OnFoodSpotsReceiveListener viewAction;
    private static final String TAG = FoodSpotsPresenter.class.getSimpleName();

    public FoodSpotsPresenter(OnFoodSpotsReceiveListener onFoodSpotsReceiveListener){
        this.viewAction = onFoodSpotsReceiveListener;
    }

    public void searchFoodSpots(Map<String, String> map){
        repository.search(map, new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 200){
                    FoodSpot[] list = (FoodSpot[]) response.body();
                    ArrayList<FoodSpotSuggestion> suggestionsList = new ArrayList<>();
                    for(FoodSpot foodSpot : list){
                        suggestionsList.add(new FoodSpotSuggestion(foodSpot.getName()));
                    }
                    viewAction.onFoodSpotSearchResult(suggestionsList);
                }




            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.toString());
                viewAction.displayMessage("Some error occurred while getting foodspots!");
            }

        });
    }

    public void getFoodSpots(Map<String, String> map){
        repository.getFoodSpots(map, new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 200){
                    ArrayList<FoodSpot> foodSpots = new ArrayList<>();
                    for(FoodSpot foodSpot : (FoodSpot[]) response.body()){
                        foodSpots.add(foodSpot);
                    }
                    viewAction.onReceiveFoodSpots(foodSpots);
                }




            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.toString());
                viewAction.displayMessage("Some error occurred while getting foodspots!");
            }

        });
    }




}
