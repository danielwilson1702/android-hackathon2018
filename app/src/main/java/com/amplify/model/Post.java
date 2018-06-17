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
    private String mPrice;

    public Post(String id, String message, long date, String dateString, long upvotes, String username, String price) {
        mID = id;
        mMessage = message;
        mDate = date;
        mDateString = dateString;

        mUpvotes = upvotes;
        mUsername = username;
        mPrice = price;
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

    public void setUpvotes(long upvotes) {
        mUpvotes = upvotes;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPrice() {
        return mPrice;
    }
}
