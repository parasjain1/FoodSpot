package com.starks.foodspots.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.starks.foodspots.models.User;

/**
 * Created by sharda on 02/01/18.
 */

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    private static int PRIVATE_MODE = 0;
    private static String PREF_NAME = "foodspots";
    private static String USER_DATA = "user_data";
    private static String TOKEN = "token_string";


    public PrefManager(Context context){
        this.context = context;
        this.pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public void putUser(User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(USER_DATA, json);
        editor.commit();
    }

    public void putToken(String token){
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken(){
        return pref.getString(TOKEN, "");   //doesn't return null (intentionally)
    }

    public boolean isLoggedIn(){
        return (pref.getString(USER_DATA, null) != null);
    }

    public User getUser(){
        if(!isLoggedIn())
            return null;

        String json = pref.getString(USER_DATA,"");
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

}
