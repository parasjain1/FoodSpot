package com.starks.foodspots.models;

import java.util.ArrayList;

/**
 * Created by sharda on 02/01/18.
 */

public class FoodSpot extends BaseModel {
    Location location;
    User owner;
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


}
