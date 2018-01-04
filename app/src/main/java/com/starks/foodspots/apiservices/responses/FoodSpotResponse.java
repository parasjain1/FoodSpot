package com.starks.foodspots.apiservices.responses;

import com.starks.foodspots.models.FoodSpot;

import java.util.ArrayList;

/**
 * Created by monikapandey on 04/01/18.
 */

public class FoodSpotResponse {
    ArrayList<FoodSpot> foodSpots;

    public ArrayList<FoodSpot> getFoodSpots() {
        return foodSpots;
    }
}
