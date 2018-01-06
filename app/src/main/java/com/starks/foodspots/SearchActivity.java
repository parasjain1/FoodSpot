package com.starks.foodspots;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.starks.foodspots.adapters.FoodSpotsListAdapter;
import com.starks.foodspots.interfaces.OnFoodSpotsReceiveListener;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.presenters.FoodSpotsPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements OnFoodSpotsReceiveListener{

    RecyclerView recyclerView;
    FoodSpotsListAdapter foodSpotsListAdapter;
    ArrayList<FoodSpot> foodSpots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.black));
        toolbar.setTitle("Searching for " + getIntent().getExtras().get("keyword"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setStatusBarColor();
        Map<String, String> map = new HashMap<>();
        Location location = MainActivity.getInstance().getLocation();
        if(location == null){
            Toast.makeText(this, "Ohho!! Couldn't locate you! Try to search again.", Toast.LENGTH_SHORT).show();
            return;
        }
        map.put("lat", location.getLatitude() + "");
        map.put("lng", location.getLongitude() + "");
        map.put("maxDistance", "9999999");
        map.put("keyword", getIntent().getExtras().getString("keyword"));
        foodSpots = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        foodSpotsListAdapter = new FoodSpotsListAdapter(SearchActivity.this, foodSpots);
        recyclerView.setAdapter(foodSpotsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new FoodSpotsPresenter(this).getFoodSpots(map);
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

    @Override
    public void onFoodSpotSearchResult(ArrayList<FoodSpotSuggestion> foodSpots) {

    }

    @Override
    public void onReceiveFoodSpots(ArrayList<FoodSpot> foodSpots) {
        this.foodSpots.addAll(foodSpots);
        foodSpotsListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : finish(); break;
        }
        return true;
    }

    void setStatusBarColor(){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
    }
}
