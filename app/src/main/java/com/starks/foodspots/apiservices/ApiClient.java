package com.starks.foodspots.apiservices;

import android.util.Log;

import com.starks.foodspots.MyApplication;
import com.starks.foodspots.utils.Constants;
import com.starks.foodspots.utils.PrefManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sharda on 02/01/18.
 */

public class ApiClient {


    //public static final String BASE_URL = "http://Custom-env.8mmqaw2dji.us-west-2.elasticbeanstalk.com/funcandi/";
    //private static final String BASE_URL = "http://192.168.0.103:9090/funcandi/";
    private static final String BASE_URL = Constants.ip +"api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
              OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(
                                new Interceptor() {
                                    @Override
                                    public Response intercept(Interceptor.Chain chain) throws IOException {
                                        Request request = chain.request().newBuilder()
                                                .addHeader("Accept", "application/JSON")
                                                .addHeader("Authorization", MyApplication.getAuthToken())
                                                .build();
                                        return chain.proceed(request);
                                    }
                                })
                        .readTimeout(120, TimeUnit.SECONDS)
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .build();


                retrofit =
                        new Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .client(defaultHttpClient)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

            }


        return retrofit;
    }


}

