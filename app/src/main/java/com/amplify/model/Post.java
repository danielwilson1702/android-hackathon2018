package com.amplify.model;

import android.text.format.DateUtils;

import com.amplify.App;

import java.io.Serializable;

public class Post implements Serializable {

    private static final long serialVersionUID = 4619726598097464749L;
    private String mID;
    private String mMessage;
    private long mDate;
    private String mDateString;
    private long mUpvotes;
    private String mUsername;

    public Post(String id, String message, long date, long upvotes, String username) {
        mID = id;
        mMessage = message;
        mDate = date;
        mDateString = DateUtils.formatDateTime(App.getInstance(), mDate,
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE);

        mUpvotes = upvotes;
        mUsername = username;
    }

    public String getMessage() {
        return mMessage;
    }

    public String getDateString() {
        return mDateString;
    }

    public long getUpvotes() {
        return mUpvotes;
    }

    public String getUsername() {
        return mUsername;
    }
}
