package com.amplify.requests;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.amplify.App;
import com.amplify.R;
import com.amplify.connection.JSONParser;
import com.amplify.connection.NetworkReceiver;
import com.amplify.connection.ServerFunctions;
import com.amplify.model.Post;
import com.amplify.requests.OnTaskCompleted.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AsyncCreatePost extends AsyncTask<Void, Void, Result> {

    private Context mContext;
    private RequestCreate mRequest;
    private String mMessage;

    private String mErrorMessage;
    private int mTag;
    private List<Post> mPosts;

    public AsyncCreatePost(Context context, RequestCreate request, String message) {
        mContext = context;
        mRequest = request;
        mMessage = message;

        execute();
    }

    @Override
    protected void onPreExecute() {
        if (!((Activity) mContext).isFinishing()) {
            if (!NetworkReceiver.getInstance().isOnline()) {
                cancel(true);
                mErrorMessage = App.getInstance().getString(R.string.error_generic_no_network);
                mRequest.onCreateFailure(Result.NO_CLIENT_CONNECTION, mErrorMessage);
            }
            mContext = null;
        } else {
            cancel(true);
        }
    }

    @Override
    protected Result doInBackground(Void... params) {
        ServerFunctions serverFunctions = new ServerFunctions();

        JSONObject postDetails = serverFunctions.createPost(mMessage, mTag);
        mPosts = new ArrayList<>();

        JSONArray posts = postDetails.optJSONArray("data");
        for(int i = 0; i < posts.length(); i++){
            JSONObject post = posts.optJSONObject(i);
            if(post != null){
                String id = post.optString("id");
                String message = post.optString("content");
                String price = post.optString("price");

                JSONObject votes = post.optJSONObject("votes");
                String voteTotal = "0";
                if(votes != null) voteTotal = votes.optString("total");
                String userName = post.optString("author");

                JSONObject dateObject = post.optJSONObject("date");
                String timeStamp = dateObject.optString("timestamp");
                String niceDate = dateObject.optString("friendly");

                mPosts.add(new Post(id, message, Long.valueOf(timeStamp), niceDate, Long.valueOf(voteTotal), userName, price));
            }
        }

        if (mPosts != null) {
            return Result.SUCCESS;
        }

        if (postDetails != null) {
            if (!postDetails.optBoolean("error")) {
                JSONArray historyArray = postDetails.optJSONArray("transactions");
                //mPosts = LLTransaction.parseFromHistoryJSON(historyArray, mLocale, null);
                if (mPosts != null) {
                    return Result.SUCCESS;
                }
            } else {
                mErrorMessage = App.getInstance().getString(R.string.error_generic_request_problem);
                return Result.FAILURE;
            }
        }

        mErrorMessage = App.getInstance().getString(R.string.error_generic_problem);
        return Result.FAILURE;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (mRequest != null) {
            if(result == Result.SUCCESS)
                mRequest.onCreateSuccess(mPosts);
            else
                mRequest.onCreateFailure(result, mErrorMessage);
        }
    }

    @Override
    protected void onCancelled() {
        JSONParser.cancelCallByTag(mTag);
        mErrorMessage = App.getInstance().getString(R.string.cancelled);
        mRequest.onCreateFailure(Result.CANCELLED, mErrorMessage);
    }

}