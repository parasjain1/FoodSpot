package com.starks.foodspots.adapters;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;
import com.starks.foodspots.FoodSpotDetailsActivity;
import com.starks.foodspots.MyApplication;
import com.starks.foodspots.R;
import com.starks.foodspots.interfaces.FoodSpotsMenuClickListener;
import com.starks.foodspots.interfaces.GetUsersListener;
import com.starks.foodspots.interfaces.VotesViewAction;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.User;
import com.starks.foodspots.models.Vote;
import com.starks.foodspots.presenters.GetUsersPresenter;
import com.starks.foodspots.presenters.VotesPresenter;
import com.starks.foodspots.utils.Constants;
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
    public void onBindViewHolder(final FoodSpotViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder " + position);
        final FoodSpot foodSpot = foodSpots.get(position);
        holder.getName().setText(foodSpot.getName());
        holder.getNumLikes().setText((foodSpot.getNumLikes()  - foodSpot.getNumDislikes())+ "");
        holder.getLikeButton().setLiked(foodSpot.getUserLiked().size() != 0);
        holder.getLikeButton().setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                holder.getNumLikes().setText(
                        (Integer.parseInt(holder.getNumLikes().getText().toString()) + 1) + ""
                );

                new VotesPresenter(new VotesViewAction() {
                    @Override
                    public void onLiked(Vote vote) {
                        foodSpot.getUserLiked().add(vote);
                    }

                    @Override
                    public void onUnlike() {

                    }
                }).addLike(foodSpot.getId());
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                if(foodSpot.getUserLiked().size() == 0)
                    return;

                holder.getNumLikes().setText(
                        (Integer.parseInt(holder.getNumLikes().getText().toString()) - 1) + ""
                );

                new VotesPresenter(new VotesViewAction() {
                    @Override
                    public void onLiked(Vote vote) {

                    }

                    @Override
                    public void onUnlike() {
                        foodSpot.getUserLiked().clear();
                    }
                }).removeLike(foodSpot.getUserLiked().get(0).getId());
            }
        });

        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodSpotDetailsActivity.class);
                Log.d(TAG, "ID = " + foodSpot.getId());
                intent.putExtra("foodSpotId", foodSpot.getId());
                context.startActivity(intent);
            }
        });

        if(foodSpot.getOwner() != MyApplication.getInstance().prefManager.getUser().getId())
            holder.getPopUpMenuButton().setVisibility(View.GONE);
        else {
            holder.getPopUpMenuButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu(v);
                }

                void showPopupMenu(View view){
                    // inflate menu
                    PopupMenu popup = new PopupMenu(context,view);
                    MenuInflater inflater = popup.getMenuInflater();
                    inflater.inflate(R.menu.foodspot_list_item_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new FoodSpotsMenuClickListener(FoodSpotsListAdapter.this, position));
                    popup.show();
                }
            });
        }

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
                holder.getUsername().setText(user.getFullName() + " (" + user.getNumFoodSpots() + " posts)");
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
                foodSpot.getLocation().getCity() + ", " +
                foodSpot.getLocation().getState());
        if(foodSpot.getGallery().size() != 0)
            Picasso.with(context).load(Constants.ip + foodSpot.getGallery().get(0).getImage()).fit().centerCrop().into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return foodSpots.size();
    }

    public FoodSpot getItem(Integer position){
        return foodSpots.get(position);
    }

    public Context getContext() { return this.context; }
}
