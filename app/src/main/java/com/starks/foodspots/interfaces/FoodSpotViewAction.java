package com.starks.foodspots.interfaces;

import com.starks.foodspots.models.FoodSpot;

import java.util.ArrayList;

/**
 * Created by monikapandey on 04/01/18.
 */

public interface FoodSpotViewAction extends BaseViewAction {
    void onFoodSpotsReceived(ArrayList<FoodSpot> foodSpots);
}
