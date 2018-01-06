package com.starks.foodspots.interfaces;

import com.starks.foodspots.models.FoodSpot;

/**
 * Created by monikapandey on 07/01/18.
 */

public interface OnAddFoodSpotListener extends BaseViewAction {
    void onFoodSpotAdded(FoodSpot foodSpot);
}
