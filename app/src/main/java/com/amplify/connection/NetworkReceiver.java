package com.amplify.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.amplify.App;

/**
 * Created by Dan on 03/12/13.
 * Class which detects whether the app is connected to the internet
 * Used in LoginFragment, and for registering and logging out
 */

public class NetworkReceiver {

    private static final String LOGTAG = "NetworkReceiver";
    private static NetworkReceiver instance = new NetworkReceiver();
    ConnectivityManager connectivityManager;
    boolean connected = false;

    public NetworkReceiver() {}

    public static NetworkReceiver getInstance() {
        return instance;
    }

    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) App.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;


        } catch (Exception e) {
            Log.v("connectivity exception", e.toString());
        }
        return connected;
    }
}
