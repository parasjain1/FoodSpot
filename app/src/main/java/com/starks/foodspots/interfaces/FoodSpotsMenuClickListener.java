package com.starks.foodspots.interfaces;

import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.starks.foodspots.FoodSpotSuggestion;
import com.starks.foodspots.R;
import com.starks.foodspots.adapters.FoodSpotsListAdapter;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.presenters.FoodSpotsPresenter;

import java.util.ArrayList;

/**
 * Created by sharda on 06/01/18.
 */

public class FoodSpotsMenuClickListener implements PopupMenu.OnMenuItemClickListener{

    FoodSpotsListAdapter foodSpotsListAdapter;
    int position;

    public FoodSpotsMenuClickListener(FoodSpotsListAdapter foodSpotsListAdapter, int position){
        super();
        this.foodSpotsListAdapter = foodSpotsListAdapter;
        this.position = position;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete :
                new FoodSpotsPresenter(new OnFoodSpotsReceiveListener() {
                    @Override
                    public void onFoodSpotSearchResult(ArrayList<FoodSpotSuggestion> foodSpots) {

                    }

                    @Override
                    public void onReceiveFoodSpots(ArrayList<FoodSpot> foodSpots) {

                    }

                    @Override
                    public void onReceiveFoodSpot(FoodSpot foodSpot) {

                    }

                    @Override
                    public void onDelete() {
                        foodSpotsListAdapter.getItemSet().remove(position);
                        foodSpotsListAdapter.notifyItemRemoved(position);
                        foodSpotsListAdapter.notifyItemRangeChanged(position, foodSpotsListAdapter.getItemCount());
                        displayMessage("Deleted " + foodSpotsListAdapter.getItem(position).getName());
                    }

                    @Override
                    public void displayMessage(String message) {
                        Toast.makeText(foodSpotsListAdapter.getContext(), message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void showLoader() {

                    }

                    @Override
                    public void hideLoader() {

                    }

                    @Override
                    public void showNetworkTimeoutError() {

                    }

                    @Override
                    public void showNoNetworkException() {

                    }
                }).deleteFoodSpot(foodSpotsListAdapter.getItem(position).getId());
                break;
            case R.id.edit :
                break;
        }

        return true;
    }
}
