package com.starks.foodspots;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.starks.foodspots.interfaces.OnFoodSpotsReceiveListener;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.FoodspotImage;
import com.starks.foodspots.presenters.FoodSpotsPresenter;
import com.starks.foodspots.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodSpotDetailsActivity extends BaseActivity implements OnFoodSpotsReceiveListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_spot_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Integer foodSpotId = getIntent().getExtras().getInt("foodSpotId");
        new FoodSpotsPresenter(this).getFoodSpot(foodSpotId+"");
        Toast.makeText(this, getIntent().getExtras().getInt("foodSpotId") + "", Toast.LENGTH_SHORT).show();
    }

    void initViews(){
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
    }

    void populateImageSlider(Map<String, String> map){
        for(String name : map.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(map.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(FoodSpotDetailsActivity.this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
    }

    @Override
    public void onFoodSpotSearchResult(ArrayList<FoodSpotSuggestion> foodSpots) {

    }

    @Override
    public void onReceiveFoodSpots(ArrayList<FoodSpot> foodSpots) {

    }

    @Override
    public void onReceiveFoodSpot(FoodSpot foodSpot) {
        Map<String, String> map = new HashMap<>();
        int counter = 0;
        for(FoodspotImage foodspotImage : foodSpot.getGallery())
            map.put(counter++ + "", Constants.ip + foodspotImage.getImage());
        populateImageSlider(map);
    }

    @Override
    public void onDelete() {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
