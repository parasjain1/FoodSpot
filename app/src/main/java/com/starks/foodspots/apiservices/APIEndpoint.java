package com.starks.foodspots.apiservices;

import android.util.Log;

import com.starks.foodspots.apiservices.responses.InfoResponse;
import com.starks.foodspots.apiservices.responses.LoginResponse;
import com.starks.foodspots.models.Comment;
import com.starks.foodspots.models.FoodSpot;
import com.starks.foodspots.models.User;
import com.starks.foodspots.models.Vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by sharda on 02/01/18.
 */

public interface APIEndpoint {

    @POST("users/")
    @Multipart
    Call<User> signUp(@PartMap() Map<String, RequestBody> map);

    @POST("login/")
    @FormUrlEncoded
    Call<LoginResponse> login(@FieldMap() Map<String, String> map);


    @POST("foodspots/vote/")
    @FormUrlEncoded
    Call<Vote> addVote(@FieldMap() Map<String, String> map);

    @PUT("foodspots/vote/{voteId}/")
    @FormUrlEncoded
    Call<Vote> editVote(@Path("voteId") String voteId, @FieldMap() Map<String, String> map);

    @DELETE("foodspots/vote/{voteId}/")
    Call<Vote> deleteVote(@Path("voteId") String voteId);

    @POST("foodspots/comment/")
    @FormUrlEncoded
    Call<Comment> addComment(@FieldMap() Map<String, String> map);

    @PUT("foodspots/comment/{commentId}/")
    @FormUrlEncoded
    Call<Comment> editComment(@Path("commentId") String commentId, @FieldMap() Map<String, String> map);

    @DELETE("foodspots/comment/{commentId}/")
    Call<Comment> deleteComment(@Path("commentId") String commentId);

    @POST("foodspots/")
    @Multipart
    Call<FoodSpot> createFoodSpot(@PartMap() Map<String, RequestBody> map);

    @GET("foodspots")
    Call<FoodSpot[]> getFoodSpots(@QueryMap() Map<String, String> map);

    @GET("foodspots/{foodSpotId}")
    Call<FoodSpot> getFoodSpot(@Path("foodSpotId") String foodSpotId);

    @PUT("foodspots/{foodSpotId}/")
    @Multipart
    Call<FoodSpot> editFoodSpot(@Path("foodSpotId") String foodSpotId, @PartMap() Map<String, RequestBody> map);

    @DELETE("foodspots/{foodSpotId}/")
    Call<FoodSpot> deleteFoodSpot(@Path("foodSpotId") String foodSpotId);

    @GET("foodspots/travel/")
    Call<InfoResponse> foodSpotsTravelling(@QueryMap() Map<String, String> map);

    @GET("users")
    Call<User[]> getUsers(@QueryMap() Map<String, String> map);

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") String userId);

    @PUT("users/{userId}/")
    @Multipart
    Call<User> editUser(@Path("userId") String userId, @PartMap() Map<String, RequestBody> map);

}
