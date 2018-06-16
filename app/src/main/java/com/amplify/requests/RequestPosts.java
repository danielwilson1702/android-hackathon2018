package com.amplify.requests;

import com.amplify.model.Post;

import java.util.List;

public interface RequestPosts extends OnTaskCompleted {
    void onPostsFailure(Result result, String errorMessage);
    void onPostsSuccess(List<Post> posts);
}
