package com.starks.foodspots.interfaces;

import com.starks.foodspots.models.User;

import java.util.ArrayList;

/**
 * Created by sharda on 05/01/18.
 */

public interface GetUsersListener extends BaseViewAction {
    void onReceiveUsers(ArrayList<User> users);
    void onReceiveUser(User user);
}
