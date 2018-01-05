package com.starks.foodspots.models;

import java.util.ArrayList;

/**
 * Created by sharda on 02/01/18.
 */

public class FoodSpot extends BaseModel {
    Location location;
    Integer owner;
    ArrayList<FoodspotImage> gallery;
    ArrayList<Vote> recentLikes;
    ArrayList<Vote> recentDislikes;
    Integer numLikes;
    Integer numDislikes;
    ArrayList<Comment> comments;
    String created_date_time;
    String name;
    String rating;
    String contact;
    String description;
    String timestamp;

    public Location getLocation() {
        return location;
    }

    public Integer getOwner() {
        return owner;
    }

    public ArrayList<FoodspotImage> getGallery() {
        return gallery;
    }

    public ArrayList<Vote> getRecentLikes() {
        return recentLikes;
    }

    public ArrayList<Vote> getRecentDislikes() {
        return recentDislikes;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public Integer getNumDislikes() {
        return numDislikes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getCreated_date_time() {
        return created_date_time;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
