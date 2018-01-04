package com.starks.foodspots.interfaces;

import com.starks.foodspots.models.User;

/**
 * Created by sharda on 02/01/18.
 */

public interface SignupViewAction extends BaseViewAction {
    void onSignUp(User user);
}
