package com.amplify.requests;

import com.amplify.model.Post;

import java.util.List;

public interface RequestCreate extends OnTaskCompleted {
    void onCreateFailure(Result result, String errorMessage);
    void onCreateSuccess(List<Post> posts);
}