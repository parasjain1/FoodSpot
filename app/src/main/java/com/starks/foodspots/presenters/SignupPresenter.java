package com.starks.foodspots.presenters;

import com.starks.foodspots.interfaces.SignupViewAction;

import java.util.HashMap;

/**
 * Created by sharda on 02/01/18.
 */

public class SignupPresenter {

    SignupViewAction viewAction;

    public SignupPresenter(SignupViewAction signupViewAction){
        this.viewAction = signupViewAction;
    }

    void signup(HashMap<String, String> data){

    }
}
