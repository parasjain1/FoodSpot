package com.starks.foodspots.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.starks.foodspots.R;
import com.starks.foodspots.interfaces.GetUsersListener;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.User;
import com.starks.foodspots.presenters.GetUsersPresenter;
import com.starks.foodspots.viewholders.FoodSpotViewHolder;

import java.util.ArrayList;

/**
 * Created by monikapandey on 04/01/18.
 */

public class FoodSpotsListAdapter extends RecyclerView.Adapter<FoodSpotViewHolder> {

    private Context context;
    private ArrayList<FoodSpot> foodSpots;
    private LayoutInflater inflater;
    private static final String TAG = FoodSpotsListAdapter.class.getSimpleName();

    public FoodSpotsListAdapter(Context context, ArrayList<FoodSpot> foodSpots){
        this.context = context;
        this.foodSpots = foodSpots;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public FoodSpotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.foodspot_item,parent,false);
        FoodSpotViewHolder recyclerViewHolder=new FoodSpotViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final FoodSpotViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        final FoodSpot foodSpot = foodSpots.get(position);
        holder.getName().setText(foodSpot.getName());

        GetUsersListener getUsersListener = new GetUsersListener() {
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
            public void onReceiveUsers(ArrayList<User> users) {

            }

            @Override
            public void onReceiveUser(final User user) {
                holder.getUsername().setText(user.getName());
                holder.getUsername().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // open user profile
                        Toast.makeText(context, "Add StartActivity code to onClick method in FoodSpotsListAdapter", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };

        new GetUsersPresenter(getUsersListener).getUser(foodSpot.getOwner());

        holder.getLocation().setText(
                foodSpot.getLocation().getStreetAddress() +
                foodSpot.getLocation().getCity() +
                foodSpot.getLocation().getState());
        if(foodSpot.getGallery().size() != 0)
            Picasso.with(context).load(foodSpot.getGallery().get(0).getImage()).fit().into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return foodSpots.size();
    }
}
