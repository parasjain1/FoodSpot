package com.starks.foodspots.interfaces;

/**
 * Created by sharda on 02/01/18.
 */

public interface BaseViewAction {
    void displayMessage(String message);
    void showLoader();
    void hideLoader();
    void showNetworkTimeoutError();
    void showNoNetworkException();
}
