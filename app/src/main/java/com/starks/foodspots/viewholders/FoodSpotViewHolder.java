package com.starks.foodspots.viewholders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
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
    TextView numLikes;
    ImageView imageView;
    LikeButton likeButton;
    CardView cardView;
    ImageButton popUpMenuButton;
    ImageView commentButton;
    TextView numComments;


    public FoodSpotViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        location = (TextView) itemView.findViewById(R.id.location);
        username = (TextView) itemView.findViewById(R.id.username);
        imageView = (ImageView) itemView.findViewById(R.id.imageview);
        likeButton = (LikeButton) itemView.findViewById(R.id.likebutton);
        numLikes = (TextView) itemView.findViewById(R.id.numLikes);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        popUpMenuButton = (ImageButton) itemView.findViewById(R.id.popUpMenuButton);
        commentButton = (ImageView) itemView.findViewById(R.id.commentButton);
        numComments = (TextView) itemView.findViewById(R.id.numComments);

    }

    public ImageButton getPopUpMenuButton() {
        return popUpMenuButton;
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

    public TextView getNumLikes() {
        return numLikes;
    }

    public CardView getCardView() {
        return cardView;
    }

    public ImageView getCommentButton() {
        return commentButton;
    }

    public TextView getNumComments() {
        return numComments;
    }
}
