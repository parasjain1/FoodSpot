package com.starks.foodspots.models;

/**
 * Created by sharda on 02/01/18.
 */

public class Comment extends BaseModel{
    String created_date_time;
    String text;
    String timestamp;
    Integer owner;
    Integer foodSpot;
}
