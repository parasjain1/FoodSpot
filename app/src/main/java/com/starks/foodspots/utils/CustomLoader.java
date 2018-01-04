package com.starks.foodspots.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import dmax.dialog.SpotsDialog;

/**
 * Created by sharda on 30/03/17.
 */

public class CustomLoader {

    Context context;
    String message;

    android.app.AlertDialog progress;

    public CustomLoader(Context context)
    {
        this.context = context;
        this.message = "";
        progress = new SpotsDialog(context);
        progress.setCancelable(false);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public CustomLoader(Context context, String message)
    {
        this.context = context;
        this.message = message;
    }

    public void showLoader() {
        progress.setTitle(message);
        if(progress.isShowing())
            progress.hide();
        progress.show();
    }

    public void hideLoader() {
        if(progress.isShowing()) {
            progress.hide();
            progress.dismiss();
        }
    }
}
