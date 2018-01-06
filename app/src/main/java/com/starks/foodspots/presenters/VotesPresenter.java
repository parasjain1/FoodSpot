package com.starks.foodspots.presenters;

import android.util.Log;

import com.starks.foodspots.apiservices.repositories.Repository;
import com.starks.foodspots.interfaces.VotesViewAction;
import com.starks.foodspots.models.Vote;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sharda on 06/01/18.
 */

public class VotesPresenter {

    VotesViewAction viewAction;
    Repository repository = new Repository();
    private static final String TAG = VotesPresenter.class.getSimpleName();

    public VotesPresenter(VotesViewAction votesViewAction){
        viewAction = votesViewAction;
    }

    public void addLike(Integer foodSpotId){
        Map<String, String> map = new HashMap<>();
        map.put("value", "1");
        map.put("foodSpot", foodSpotId + "");
        repository.addVote(map, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                viewAction.onLiked((Vote) response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void removeLike(Integer voteId){
        repository.deleteVote(voteId.toString(), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d(TAG, response.code() + " " + response.message());
                viewAction.onUnlike();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
