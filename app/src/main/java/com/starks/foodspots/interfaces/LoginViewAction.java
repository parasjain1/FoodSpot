package com.starks.foodspots.interfaces;

import com.starks.foodspots.apiservices.responses.LoginResponse;

/**
 * Created by sharda on 02/01/18.
 */

public interface LoginViewAction extends BaseViewAction {
    void onLogin(LoginResponse loginResponse);
}
