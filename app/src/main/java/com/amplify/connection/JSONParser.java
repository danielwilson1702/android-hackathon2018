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
    public static final int HTTP_POST = 0;
    public static final int HTTP_POST_JSON = 1;
    public static final int HTTP_POST_SECURED = 2;
    public static final int HTTP_PUT = 3;
    public static final int HTTP_PUT_JSON = 4;
    public static final int HTTP_GET = 5;
    public static final int HTTP_GET_SECURED = 6;
    public static final int HTTP_GET_CLOVER = 7;
    public static final int HTTP_POST_CLOVER = 8;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    final static OkHttpClient client = new OkHttpClient();
    OkHttpClient slowBuilder = client.newBuilder().readTimeout(20, TimeUnit.SECONDS).build();
    private static final String TAG = "JSONParser";

    public JSONParser() {
    }

    public static JSONObject getJSONFromHttpPost(String url, Map<String, String> params) {
        return getJSONFromHttpPost(url, params, null, App.getInstance().getNextUniqueIndex(), HTTP_POST_SECURED, false, true);
    }

    public static JSONObject getSlowJSONFromHttpPost(String url, Map<String, String> params) {
        return getJSONFromHttpPost(url, params, null, App.getInstance().getNextUniqueIndex(), HTTP_POST_SECURED, true, true);
    }

    public static JSONObject getNonRetryableJSONFromHttpPost(String url, Map<String, String> params, int tag) {
        return getJSONFromHttpPost(url, params, null, tag, HTTP_POST_SECURED, false, false);
    }

    public static JSONObject getJSONFromHttpJSONPost(String url, JSONObject json, int tag) {
        return getJSONFromHttpPost(url, null, json, tag, HTTP_POST_JSON, false, true);
    }

    public static JSONObject getJSONFromHttpPost(String url, Map<String, String> params, int tag) {
        return getJSONFromHttpPost(url, params, null, tag, HTTP_POST_SECURED, false, true);
    }

    public static JSONObject getJSONFromHttpPost(String url, Map<String, String> params, int tag, @RequestTypeDef int requestType) {
        return getJSONFromHttpPost(url, params, null, tag, requestType, false, true);
    }

    public static JSONObject getJSONFromHttpPut(String url, Map<String, String> params, int tag) {
        return getJSONFromHttpPost(url, params, null, tag, HTTP_PUT, false, true);
    }

    public static JSONObject getJSONFromHttpJSONPut(String url, JSONObject json, int tag) {
        return getJSONFromHttpPost(url, null, json, tag, HTTP_PUT_JSON, false, true);
    }

    public static JSONObject getJSONFromHttpPost(String url, @Nullable Map<String, String> params, @Nullable JSONObject json, int tag, @RequestTypeDef int requestType, boolean slow, boolean allowRetry) {
        FormBody.Builder formBody = new FormBody.Builder();
        if (params != null) {
            if(json != null) {
                throw new RuntimeException("We can't post JSON and params, it's one of the other");
            }
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
            case HTTP_POST_SECURED:
                body = formBody.build();
                request = new Request.Builder()
                        .url(url)
                        .header("Authorisation", App.getApiKey())
                        .post(body)
                        .tag(tag)
                        .build();
                break;
            case HTTP_POST:
                body = formBody.build();
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .tag(tag)
                        .build();
                break;
            case HTTP_POST_JSON:
                if (json != null) {
                    body = RequestBody.create(JSON, json.toString());
                    request = new Request.Builder()
                            .url(url)
                            .header("Authorisation", App.getApiKey())
                            .post(body)
                            .tag(tag)
                            .build();
                }
                break;
            case HTTP_POST_CLOVER:
                String secret = "";//App.getAppSecret(url);
                if(!isEmpty(secret) && json != null) {
                    body = RequestBody.create(JSON, json.toString());
                    request = new Request.Builder()
                            .url(url)
                            .header("Authorization", "Bearer " + secret)
                            .post(body)
                            .tag(tag)
                            .build();
                }
                else{
                    Log.e(TAG, "The clover token was null!");
                }
                break;
            case HTTP_PUT:
                body = formBody.build();
                request = new Request.Builder()
                        .url(url)
                        .header("Authorisation", App.getApiKey())
                        .put(body)
                        .tag(tag)
                        .build();
                break;
            case HTTP_PUT_JSON:
                if (json != null) {
                    body = RequestBody.create(JSON, json.toString());
                    request = new Request.Builder()
                            .url(url)
                            .header("Authorisation", App.getApiKey())
                            .put(body)
                            .tag(tag)
                            .build();
                }
                break;
            case HTTP_GET_SECURED:
                break;
            case HTTP_GET:
                break;
        }

        Response response = null;
        try {
            if (request != null && request.url() != null)
                Log.w(TAG, "Calling " + request.url().toString());
            Call call;
            if(slow)
                call = client.newBuilder().readTimeout(60, TimeUnit.SECONDS).build().newCall(request);
            else if(!allowRetry) {
                call = client.newBuilder().retryOnConnectionFailure(false).build().newCall(request);
            }
            else
                call = client.newCall(request);
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

    public static void cancelCallByTag(int tag) {
        List<Call> calls = client.dispatcher().runningCalls();
        for (Call c : calls) {
            if (c.request() != null && c.request().tag() != null && c.request().tag().equals(tag)) {
                c.cancel();
                break;
            }
        }
    }

    public static JSONObject getJSONFromHttpGetWithHeaderParams(String url, List<String> params, Map<String, String> headerParams, int tag) {
        return getJSONFromHttpGet(url, params, headerParams, tag, HTTP_GET_SECURED, false);
    }

    public static JSONObject getJSONFromHttpGet(String url, List<String> params, int tag) {
        return getJSONFromHttpGet(url, params, null, tag, HTTP_GET_SECURED, false);
    }

    public static JSONObject getSlowJSONFromHttpGet(String url, List<String> params, int tag) {
        return getJSONFromHttpGet(url, params, null, tag, HTTP_GET_SECURED, true);
    }

    public static JSONObject getJSONFromHttpGet(String url, List<String> params) {
        return getJSONFromHttpGet(url, params, null, App.getInstance().getNextUniqueIndex(), HTTP_GET_SECURED, false);
    }

    public static JSONObject getJSONFromHttpGet(String url, List<String> params, @Nullable Map<String, String> headerParams,
                                                int tag, @RequestTypeDef int requestType, boolean slow) {
        if(params != null) {
            for (int i = 0; i < params.size(); i++) {
                url += ("/" + params.get(i));
            }
        }

        Log.d("Getting url....", url);

        Request request = null;
        switch (requestType) {
            case HTTP_POST_SECURED:
            case HTTP_POST:
            case HTTP_PUT:
                break;
            case HTTP_GET_SECURED:
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                builder.header("Authorisation", App.getApiKey());


                if(headerParams != null && headerParams.size() > 0){
                    for (Map.Entry<String, String> entry : headerParams.entrySet()) {
                        builder.header(entry.getKey(), entry.getValue());

                    }
                }

                builder.tag(tag);
                request = builder.build();
                break;
            case HTTP_GET_CLOVER:
                String cloverToken = "";//App.getCloverToken();
                if(!isEmpty(cloverToken)) {
                    request = new Request.Builder()
                            .url(url)
                            .header("Authorization", "Bearer " + cloverToken)
                            .tag(tag)
                            .build();
                }
                else{
                    Log.e(TAG, "The clover token was null!");
                }
                break;
            case HTTP_GET:
                request = new Request.Builder()
                        .url(url)
                        .tag(tag)
                        .build();
                break;
            case HTTP_POST_JSON:
                break;
            case HTTP_POST_CLOVER:
                break;
        }

        Response response = null;
        try {
            if(request != null) {
                if(slow){
                    response = client.newBuilder().readTimeout(30, TimeUnit.SECONDS).build().newCall(request).execute();
                }
                else{
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

    public static List<String> getListFromJSONArray(JSONArray array) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.optString(i));
        }
        return list;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            HTTP_POST,
            HTTP_POST_JSON,
            HTTP_POST_SECURED,
            HTTP_PUT,
            HTTP_PUT_JSON,
            HTTP_GET,
            HTTP_GET_SECURED,
            HTTP_GET_CLOVER,
            HTTP_POST_CLOVER
    })
    public @interface RequestTypeDef {
    }
}
