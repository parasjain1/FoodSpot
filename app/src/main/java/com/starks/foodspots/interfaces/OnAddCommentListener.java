package com.starks.foodspots.interfaces;

import com.starks.foodspots.models.Comment;

/**
 * Created by sharda on 07/01/18.
 */

public interface OnAddCommentListener extends BaseViewAction {
    void onAddComment(Comment comment);
}
