package com.starks.foodspots.models;

/**
 * Created by sharda on 02/01/18.
 */

public class Vote extends BaseModel{
    Integer value;
    Integer owner;
    Integer foodSpot;
    String timestamp;

    public Integer getValue() {
        return value;
    }

    public Integer getOwner() {
        return owner;
    }

    public Integer getFoodSpot() {
        return foodSpot;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
