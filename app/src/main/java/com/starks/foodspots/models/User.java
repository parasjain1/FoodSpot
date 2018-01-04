package com.starks.foodspots.models;

/**
 * Created by sharda on 02/01/18.
 */

public class User extends BaseModel {
    String name;
    String username;
    String email;
    Float credits;
    Integer numFoodSpots;
    String password;

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Float getCredits() {
        return credits;
    }

    public Integer getNumFoodSpots() {
        return numFoodSpots;
    }
}
