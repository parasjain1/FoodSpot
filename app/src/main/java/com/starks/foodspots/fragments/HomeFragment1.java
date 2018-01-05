package com.starks.foodspots.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starks.foodspots.FoodSpotSuggestion;
import com.starks.foodspots.R;
import com.starks.foodspots.adapters.FoodSpotsListAdapter;
import com.starks.foodspots.interfaces.OnFoodSpotsReceiveListener;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.presenters.FoodSpotsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharda on 05/01/18.
 */

public class HomeFragment1 extends Fragment {

    private static final String TAG = HomeFragment1.class.getSimpleName();
    RecyclerView recyclerView;
    FoodSpotsListAdapter foodSpotsListAdapter;

    public static HomeFragment1 newInstance() {
        return new HomeFragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final ArrayList<FoodSpot> allFoodSpots = new ArrayList<>();
        foodSpotsListAdapter = new FoodSpotsListAdapter(getContext(), allFoodSpots);
        recyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(foodSpotsListAdapter);

        FoodSpotsPresenter foodSpotsPresenter = new FoodSpotsPresenter(new OnFoodSpotsReceiveListener() {
            @Override
            public void onFoodSpotSearchResult(ArrayList<FoodSpotSuggestion> foodSpots) {

            }

            @Override
            public void onReceiveFoodSpots(ArrayList<FoodSpot> foodSpots) {
                Log.d(TAG, foodSpots.size() + " foodSpots received");
                allFoodSpots.addAll(foodSpots);
                foodSpotsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void displayMessage(String message) {

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
        });

        Map<String, String> map = new HashMap<>();
        map.put("lat", "12");
        map.put("lng", "12");
        map.put("maxDistance", "999999");
        foodSpotsPresenter.getFoodSpots(map);
    }

}
