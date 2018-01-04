package com.starks.foodspots.services;

import android.os.Handler;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 03/01/18.
 */

public class CustomFirebaseIdService extends FirebaseInstanceIdService{

        private static final String TAG = FirebaseInstanceIdService.class.getSimpleName();
        private static String token = null;

        //generates token on reinstall or uninstall of app
        @Override
        public void onTokenRefresh()
        {
            token = FirebaseInstanceId.getInstance().getToken();
            FirebaseMessaging.getInstance().subscribeToTopic("news");
            Log.d(TAG, "Refreshed token: " + token);
            if(token!=null) {

                sendRegistrationToServer(token);
            }
            else {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onTokenRefresh();
                    }
                }, 5000);

            }
        }


        public static String getToken()
        {
            return FirebaseInstanceId.getInstance().getToken();
        }



        /**
         * Persist token to third-party servers.
         *
         * Modify this method to associate the user's FCM InstanceID token with any server-side account
         * maintained by your application.
         *
         * @param token The new token.
         */
        private void sendRegistrationToServer(final String token) {

        }
}
