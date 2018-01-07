package com.starks.foodspots.interfaces;

import com.starks.foodspots.FoodSpotSuggestion;
import com.starks.foodspots.models.FoodSpot;

import java.util.ArrayList;

/**
 * Created by monikapandey on 04/01/18.
 */

public interface OnFoodSpotsReceiveListener extends BaseViewAction {
    void onFoodSpotSearchResult(ArrayList<FoodSpotSuggestion> foodSpots);
    void onReceiveFoodSpots(ArrayList<FoodSpot> foodSpots);
    void onReceiveFoodSpot(FoodSpot foodSpot);
    void onDelete();
}
