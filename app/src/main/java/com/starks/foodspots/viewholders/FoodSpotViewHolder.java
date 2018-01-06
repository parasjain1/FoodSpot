package com.starks.foodspots.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.starks.foodspots.R;

import org.w3c.dom.Text;

/**
 * Created by sharda on 05/01/18.
 */

public class FoodSpotViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView location;
    TextView username;
    ImageView imageView;
    LikeButton likeButton;

    public FoodSpotViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        location = (TextView) itemView.findViewById(R.id.location);
        username = (TextView) itemView.findViewById(R.id.username);
        imageView = (ImageView) itemView.findViewById(R.id.imageview);
        likeButton = (LikeButton) itemView.findViewById(R.id.likebutton);
    }

    public TextView getName() {
        return name;
    }

    public TextView getLocation() {
        return location;
    }

    public TextView getUsername() {
        return username;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public LikeButton getLikeButton() {
        return likeButton;
    }


}