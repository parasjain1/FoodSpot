package com.starks.foodspots.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.starks.foodspots.FoodSpotSuggestion;
import com.starks.foodspots.MainActivity;
import com.starks.foodspots.MyApplication;
import com.starks.foodspots.R;
import com.starks.foodspots.adapters.FoodSpotsListAdapter;
import com.starks.foodspots.interfaces.OnFoodSpotsReceiveListener;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.User;
import com.starks.foodspots.presenters.FoodSpotsPresenter;
import com.starks.foodspots.services.LocationService;

import java.io.IOException;
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
    Boolean showUsersPosts;

    public HomeFragment1(){

    }

    public HomeFragment1(boolean showUsersPosts){
        super();
        this.showUsersPosts = false;
        this.showUsersPosts = showUsersPosts;
    }

    public static HomeFragment1 newInstance(Boolean showUsersPosts) {
        return new HomeFragment1(showUsersPosts);
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
                .setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setHasFixedSize(true);
        //setup materialviewpager




        //Use this now
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setAdapter(foodSpotsListAdapter);

        final FoodSpotsPresenter foodSpotsPresenter = new FoodSpotsPresenter(new OnFoodSpotsReceiveListener() {
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
            public void onDelete() {

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



        final Map<String, String> map = new HashMap<>();
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Location location = ((MainActivity) getActivity()).getLocation();
                if(location == null)
                    handler.postDelayed(this,1000);
                else {
                    Log.d(TAG, "lat : " + location.getLatitude());
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        Address address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                        Log.d(TAG, address.getLocality());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    map.put("lat", location.getLatitude() + "");
                    map.put("lng", location.getLongitude() + "");
                    map.put("maxDistance", "999999");
                    if(showUsersPosts != null && showUsersPosts) {
                        User user = MyApplication.getInstance().prefManager.getUser();
                        if (user != null)
                            map.put("username", user.getUsername());
                    }
                    foodSpotsPresenter.getFoodSpots(map);
                }
            }
        };
        handler.postDelayed(runnable,0);


    }


}
