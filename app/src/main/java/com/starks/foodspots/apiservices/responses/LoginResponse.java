package com.starks.foodspots.apiservices.responses;

/**
 * Created by sharda on 02/01/18.
 */

public class LoginResponse {

    String token;
    String[] non_field_errors;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String[] getNon_field_errors() {
        return non_field_errors;
    }
}
