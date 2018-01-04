package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.FoodSpotSuggestion;
import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.apiservices.responses.FoodSpotResponse;
import com.starks.foodspots.interfaces.FoodSpotSearchListener;
import com.starks.foodspots.models.FoodSpot;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by monikapandey on 04/01/18.
 */

public class FoodSpotsSearchPresenter {

    private Repository repository = new Repository();
    private FoodSpotSearchListener viewAction;
    private static final String TAG = FoodSpotsSearchPresenter.class.getSimpleName();

    public FoodSpotsSearchPresenter(FoodSpotSearchListener foodSpotSearchListener){
        this.viewAction = foodSpotSearchListener;
    }

    public void searchFoodSpots(Map<String, String> map){
        repository.getFoodSpots(map, new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                if(response.code() == 200){
                    ArrayList<FoodSpot> list = ((FoodSpotResponse) response.body()).getFoodSpots();
                    ArrayList<FoodSpotSuggestion> suggestionsList = new ArrayList<>();
                    for(FoodSpot foodSpot : list){
                        suggestionsList.add(new FoodSpotSuggestion(foodSpot.getName()));
                    }
                    viewAction.onFoodSpotSearchResult(suggestionsList);
                }




            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, t.getMessage());
                viewAction.displayMessage("Some error occurred while getting foodspots!");
            }

        });
    }




}
