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

import java.util.List;

public class AsyncFetchPosts extends AsyncTask<Void, Void, Result> {

    private Context mContext;
    private RequestPosts mRequest;
    private String mType;
    private double mLat;
    private double mLong;
    private int mMeterRange;

    private List<Post> mPosts;
    private String mErrorMessage;
    private int mTag;

    public AsyncFetchPosts(Context context, RequestPosts request, String type,
                           double lat, double lng, int meterRange) {
        mContext = context;
        mRequest = request;

        mType = type;
        mLat = lat;
        mLong = lng;
        mMeterRange = meterRange;

        execute();
    }

    @Override
    protected void onPreExecute() {
        if (!((Activity) mContext).isFinishing()) {
            if (!NetworkReceiver.getInstance().isOnline()) {
                cancel(true);
                mErrorMessage = App.getInstance().getString(R.string.error_generic_no_network);
                mRequest.onPostsFailure(Result.NO_CLIENT_CONNECTION, mErrorMessage);
            }
            mContext = null;
        } else {
            cancel(true);
        }
    }

    @Override
    protected Result doInBackground(Void... params) {
        ServerFunctions serverFunctions = new ServerFunctions();

        JSONObject postDetails = serverFunctions.getPosts(mType, mLat, mLong, mMeterRange, mTag);
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
                mRequest.onPostsSuccess(mPosts);
            else
                mRequest.onPostsFailure(result, mErrorMessage);
        }
    }

    @Override
    protected void onCancelled() {
        JSONParser.cancelCallByTag(mTag);
        mErrorMessage = App.getInstance().getString(R.string.cancelled);
        mRequest.onPostsFailure(Result.CANCELLED, mErrorMessage);
    }

}