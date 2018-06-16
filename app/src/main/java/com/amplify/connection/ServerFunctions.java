package com.amplify.connection;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerFunctions {

    private static final String GET_POSTS = NEW_URL() + "threads?";

    private static String NEW_URL() {
        return "https://dev.loylap.com/api/v1/";
    }

    public JSONObject getPosts(String type, double lat, double lng, int range, int tag) {
        Map<String, String> params = new HashMap<>();

        params.put("type", type);
        params.put("lat", String.valueOf(lat));
        params.put("long", String.valueOf(lng));
        params.put("range", String.valueOf(range));

        String url = GET_POSTS;
        Iterator it = params.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(pair != null && pair.getKey() != null && pair.getValue() != null) {
                String key = pair.getKey().toString();
                String value = pair.getValue().toString();

                url += (key + "=" + value + "&");
            }
            it.remove();
        }

        return JSONParser.getJSONFromHttpGet(url, null, tag);
    }
}


