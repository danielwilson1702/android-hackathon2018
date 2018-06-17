package com.amplify.connection;


import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amplify.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.text.TextUtils.isEmpty;

public class JSONParser {
    public static final int HTTP_GET = 5;
    public static final int HTTP_POST = 6;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    final static OkHttpClient client = new OkHttpClient();
    private static final String TAG = "JSONParser";

    public JSONParser() {
    }

    public static void cancelCallByTag(int tag) {
        List<Call> calls = client.dispatcher().runningCalls();
        for (Call c : calls) {
            if (c.request() != null && c.request().tag() != null && c.request().tag().equals(tag)) {
                c.cancel();
                break;
            }
        }
    }

    public static JSONObject getJSONFromHttpGet(String url, List<String> params, int tag) {
        return getJSONFromHttpGet(url, params, null, tag, HTTP_GET, false);
    }

    public static JSONObject getJSONFromHttpGet(String url, List<String> params, @Nullable Map<String, String> headerParams,
                                                int tag, @RequestTypeDef int requestType, boolean slow) {
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                url += ("/" + params.get(i));
            }
        }

        Log.d("Getting url....", url);

        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();

        Response response = null;
        try {
            if (request != null) {
                if (slow) {
                    response = client.newBuilder().readTimeout(30, TimeUnit.SECONDS).build().newCall(request).execute();
                } else {
                    response = client.newCall(request).execute();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            Log.w(TAG, "The response was null!");
            return null;
        } else if (!response.isSuccessful()) {
            Log.w(TAG, "The response was not successful!");
        }

        try {
            String body = response.body().string();
            Log.d("Response body", body);

            return new JSONObject(body);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject getJSONFromHttpPost(String url, @Nullable Map<String, String> params, int tag, @RequestTypeDef int requestType) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (params != null) {
            Iterator it = params.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if(pair != null && pair.getKey() != null && pair.getValue() != null) {
                    String key = pair.getKey().toString();
                    String value = pair.getValue().toString();
                    if(!isEmpty(key) && !isEmpty(value))
                        formBody.add(key, value);
                }
                it.remove();
            }
        }

        Request request = null;
        RequestBody body;
        switch (requestType) {
            case HTTP_POST:
                body = formBody.build();
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .tag(tag)
                        .build();
                break;
        }

        Response response = null;
        try {
            if (request != null && request.url() != null)
                Log.w(TAG, "Calling " + request.url().toString());
            Call call = client.newCall(request);
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            Log.w(TAG, "The response was null!");
            return null;
        } else if (!response.isSuccessful()) {
            Log.w(TAG, "The response was not successful!");
        }

        try {
            JSONObject responseBody = null;
            if (response.body() != null) {
                responseBody = new JSONObject(response.body().string());
            }
            Log.w(TAG, "Returning response body: " + responseBody);
            return responseBody;
        } catch (JSONException | IOException e) {
            Log.w(TAG, "Exception parsing response to JSON");
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getListFromJSONArray(JSONArray array) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.optString(i));
        }
        return list;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            HTTP_GET,
            HTTP_POST
    })
    public @interface RequestTypeDef {
    }
}
