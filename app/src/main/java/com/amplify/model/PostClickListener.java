package com.amplify.model;

import android.view.View;

public interface PostClickListener {
    void onPostClick(View view, Post post);
    void onCreateClick(String message);
    void onUpvoteClick(View view, Post post, boolean on);
    void onDownvoteClick(View view, Post post, boolean on);
}
