package com.starks.foodspots.interfaces;

import com.starks.foodspots.models.Vote;

/**
 * Created by sharda on 06/01/18.
 */

public interface VotesViewAction {
    void onLiked(Vote vote);
    void onUnlike();
}
