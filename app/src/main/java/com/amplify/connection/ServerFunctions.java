package com.amplify.connection;

import android.os.Build;
import android.util.Patterns;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.text.TextUtils.isEmpty;
import static com.amplify.connection.JSONParser.HTTP_POST;

public class ServerFunctions {

    private static final String SCHEDULE_DEMO = NEW_URL() + "sendDemoEmail";
    private static final String LOGIN_BUSINESS = NEW_URL() + "posLogin";
    private static final String REGISTER_BUSINESS = NEW_URL() + "registerBusiness";
    private static final String UPDATE_SCHEME_DETAILS = NEW_URL() + "updateLoyalty";
    private static final String GET_SCHEME_DETAILS = NEW_URL() + "group";
    private static final String SEND_TRANSACTION = NEW_URL() + "transaction";
    private static final String VOID_TRANSACTION = NEW_URL() + "voidTransaction";
    private static final String SEND_DIGITAL_GIFT = NEW_URL() + "sendECard";
    private static final String GET_CUSTOMER_DETAILS = NEW_URL() + "user";
    private static final String REGISTER_CUSTOMER_DETAILS = NEW_URL() + "activateAccount";
    private static final String REGISTER_CUSTOMER_DETAILS_PARTIAL = NEW_URL() + "registerCard";
    private static final String SEND_GIFT_INFO = NEW_URL() + "cloverData";
    private static final String WIPE_CARD = NEW_URL() + "resetCard";
    private static final String LINK_CARD = NEW_URL() + "linkCard";
    private static final String REJECT_CAMPAIGN = NEW_URL() + "campaignRefund";
    private static final String GET_TRANSACTION = NEW_URL() + "uniqueTransaction";
    private static final String UPDATE_CUSTOMER_DETAILS = NEW_URL() + "userLoyalty";
    private static final String SEND_TRANSACTION_LOGS = NEW_URL() + "logTransactions";
    private static final String GET_CUSTOMER_HISTORY = NEW_URL() + "analytics/customerTransactions";
    private static final String POST_MERCHANT_HISTORY = NEW_URL() + "analytics/allTransactionsPaginated";
    private static final String GET_MERCHANT_HISTORY_OVERVIEW = NEW_URL() + "analytics/dashboard";
    private static final String GET_CUSTOMER_OVERVIEW = NEW_URL() + "analytics/historyOverview";
    private static final String POST_CUSTOMERS_BY_NAME = NEW_URL() + "analytics/customersByFilters";
    private static final String PUT_SETTINGS = NEW_URL() + "business/details";

    private static String NEW_URL() {
        return "https://dev.loylap.com/api/v1/";
    }

    /**
     * Login a business. This will generate an api key.
     */
    public JSONObject login(String email, String merchantID, String authToken, String baseUrl, int tag) {
        Map<String, String> params = new HashMap<>();

        params.put("email", email);
        params.put("merchant_id", merchantID);
        params.put("access_token", authToken);
        params.put("clover_device", Build.SERIAL);
        params.put("base_url", baseUrl);

        return JSONParser.getJSONFromHttpPost(LOGIN_BUSINESS, params, tag, HTTP_POST);
    }

    /**
     * Register a new business by their mID. This will generate an api key too
     */
    public JSONObject registerBusiness(String merchantID, String ownerFirstName, String ownerLastName, String businessName, String ownerEmail,
                                       String password, String phone, String address, String website, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("business_name", businessName);
        params.put("owner_first_name", ownerFirstName);
        params.put("owner_last_name", ownerLastName);
        params.put("owner_email", ownerEmail);
        params.put("password", password);
        params.put("phone", phone);
        params.put("address", address);
        params.put("website", website);
        params.put("merchant_id", merchantID);

        return JSONParser.getJSONFromHttpPost(REGISTER_BUSINESS, params, tag, HTTP_POST);
    }

    public JSONObject updateSchemeDetails(String loyaltyType, String loyaltyAmount, String loyaltyDescription, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("loyalty_type", loyaltyType);
        params.put("reward", loyaltyAmount);
        if (!isEmpty(loyaltyDescription))
            params.put("product", loyaltyDescription);

        return JSONParser.getJSONFromHttpPut(UPDATE_SCHEME_DETAILS, params, tag);
    }

    public JSONObject getSchemeDetails(String groupID, int tag) {
        return JSONParser.getJSONFromHttpGet(GET_SCHEME_DETAILS, Collections.singletonList(groupID), tag);
    }

    public JSONObject getCustomer(String loyLapNumber, String groupID, String branchID, long orderTotal, int tag) {

        DateTime dt = new DateTime();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("user_id", loyLapNumber);
        headerParams.put("group_id", groupID);
        headerParams.put("branch_id", branchID);
        headerParams.put("transaction_total", String.valueOf(orderTotal));
        headerParams.put("local_iso_date", dt.toString(fmt));

        return JSONParser.getJSONFromHttpGetWithHeaderParams(GET_CUSTOMER_DETAILS, Collections.singletonList(loyLapNumber), headerParams, tag);
    }

    public JSONObject getCustomerByNameOrMail(String searchString, String groupID, String branchID, int tag) {

        ///analytics/customersByFilters/:group_id/:branch_id/:start/:offset/:orderBy/:reverse
        List<String> urlComponents = new ArrayList<>();
        urlComponents.add(groupID);
        urlComponents.add(branchID);
        urlComponents.add(String.valueOf(0));
        urlComponents.add(String.valueOf(100));
        urlComponents.add("null");
        urlComponents.add("false");

        StringBuilder url = new StringBuilder(POST_CUSTOMERS_BY_NAME);
        for (int i = 0; i < urlComponents.size(); i++) {
            url.append("/").append(urlComponents.get(i));
        }

        Map<String, String> params = new HashMap<>();
        if(Patterns.EMAIL_ADDRESS.matcher(searchString).matches())
            params.put("email", searchString);
        else
            params.put("name", searchString);

        return JSONParser.getJSONFromHttpPost(url.toString(), params, tag);
    }

    public JSONObject getCustomerByNickname(String searchString, String groupID, String branchID, int tag) {

        ///analytics/customersByFilters/:group_id/:branch_id/:start/:offset/:orderBy/:reverse
        List<String> urlComponents = new ArrayList<>();
        urlComponents.add(groupID);
        urlComponents.add(branchID);
        urlComponents.add(String.valueOf(0));
        urlComponents.add(String.valueOf(100));
        urlComponents.add("null");
        urlComponents.add("false");

        StringBuilder url = new StringBuilder(POST_CUSTOMERS_BY_NAME);
        for (int i = 0; i < urlComponents.size(); i++) {
            url.append("/").append(urlComponents.get(i));
        }

        Map<String, String> params = new HashMap<>();
        params.put("nickname", searchString);

        return JSONParser.getJSONFromHttpPost(url.toString(), params, tag);
    }

    public JSONObject getCustomerOverview(String customerID, int tag) {
        return JSONParser.getJSONFromHttpGet(GET_CUSTOMER_OVERVIEW, Collections.singletonList(customerID), tag);
    }

    public JSONObject getTransaction(String uid, int tag) {
        return JSONParser.getJSONFromHttpGet(GET_TRANSACTION, Collections.singletonList(uid), tag);
    }

    public JSONObject scheduleDemo(String name, String phone, String email, String businessName, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("phone", phone);
        params.put("email", email);
        params.put("business_name", businessName);
        return JSONParser.getJSONFromHttpPost(SCHEDULE_DEMO, params, tag, HTTP_POST);
    }

    public JSONObject postCloverPushNote(String address, String event, String data, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("event", event);
        params.put("data", data);
        JSONObject push = new JSONObject(params);
        return JSONParser.getJSONFromHttpPost(address, null, push, tag, JSONParser.HTTP_POST_CLOVER, false, true);
    }

    public JSONObject putSettings(String jsonArray, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("settings", jsonArray);

        return JSONParser.getJSONFromHttpPut(PUT_SETTINGS, params, tag);
    }

    public JSONObject postCloverLogs(JSONObject logs, int tag) {
        return JSONParser.getJSONFromHttpPost(SEND_TRANSACTION_LOGS, null, logs, tag, JSONParser.HTTP_POST_JSON, true, true);
    }

    public JSONObject registerFob(String id, String name, String email, String password, String gender, String dob, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("loyalty_id", id);
        params.put("full_name", name);

        if(!isEmpty(email))
            params.put("email", email);

        params.put("password", password);
        params.put("gender", gender);
        params.put("date_of_birth", dob);
        return JSONParser.getJSONFromHttpPut(REGISTER_CUSTOMER_DETAILS, params, tag);
    }

    public JSONObject registerFobPartial(String id, String name, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("card_number", id);

        return JSONParser.getJSONFromHttpPost(REGISTER_CUSTOMER_DETAILS_PARTIAL, params, tag);
    }

    public JSONObject updateCustomer(String id, String note, String creditAllowed, String personalReward, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("account_number", id);
        params.put("customer_note", note);
        params.put("credit_allowed", creditAllowed);
        //TODO Kill these magic numbers, consequence of having to send doubles instead of ints to the server
        if(!isEmpty(personalReward) && !personalReward.equals("-0.01")) {
            if(personalReward.equals("-0.02"))//Clear flag
                personalReward = "NULL";
            params.put("personal_reward", personalReward);
        }
        return JSONParser.getJSONFromHttpPut(UPDATE_CUSTOMER_DETAILS, params, tag);
    }

    public JSONObject getCloverInfo(String address, List<String> endpoint, int tag) {
        return JSONParser.getJSONFromHttpGet(address, endpoint,null, tag, JSONParser.HTTP_GET_CLOVER, false);
    }

    public JSONObject postCloverTag(String address, String tagName, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("name", tagName);
        JSONObject jsonTag = new JSONObject(params);
        return JSONParser.getJSONFromHttpPost(address, null, jsonTag, tag, JSONParser.HTTP_POST_CLOVER, false, true);
    }

    public JSONObject wipeCard(String cardNumber, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("card_number", cardNumber);

        return JSONParser.getJSONFromHttpPost(WIPE_CARD, params, tag);
    }

    public JSONObject rejectCampaign(String uuid, long campaignID, int tag) {
        List<String> params = new ArrayList<>();
        params.add(uuid);
        params.add(String.valueOf(campaignID));

        return JSONParser.getJSONFromHttpGet(REJECT_CAMPAIGN, params, tag);
    }

    public JSONObject linkCard(String newID, String originalID, String cvv, String disableOriginal, int tag) {
        Map<String, String> params = new HashMap<>();
        params.put("card_number", newID);
        params.put("customer_id", originalID);
        if(!isEmpty(cvv))
            params.put("cvv", cvv);

        params.put("disable_old", disableOriginal);

        return JSONParser.getJSONFromHttpPut(LINK_CARD, params, tag);
    }

    public JSONObject getCustomerHistory(String customerID, int index, int offset, long startDate, long endDate, int tag) {
        ///customerID/startDate/endDate/start/offset/orderBy/reverse
/*      customerID: basic user id of a customer
        startDate: unix timestamp, start of the interval
        endDate: unix timestamp, end of the interval
        start: integer, from which index you want to show the result set
        offset: integer, how many items do you want to show
        orderBy: string, options: ‘transaction_type’, ‘branch_name’, ‘loyaltyType’, ‘amount’, ‘time’, ‘null’ (which defaults to time)
        reverse: boolean (passed as string), decide to show ASC or DESC ordering of the above column*/

        List<String> params = new ArrayList<>();
        params.add(customerID);
        params.add(String.valueOf(startDate / 1000));
        params.add(String.valueOf(endDate / 1000));
        params.add(String.valueOf(index));
        params.add(String.valueOf(offset));
        params.add("time");
        params.add("true");

        return JSONParser.getSlowJSONFromHttpGet(GET_CUSTOMER_HISTORY, params, tag);
    }

    public JSONObject getMerchantHistory(String branchID, int index, int offset, long startDate, long endDate, int tag) {
        List<String> params = new ArrayList<>();
        params.add(branchID);
        params.add(String.valueOf(startDate));
        params.add(String.valueOf(endDate));
        params.add(String.valueOf(index));
        params.add(String.valueOf(offset));
        //        orderBy: string, options: ‘transaction_type’, ‘account_number’, ‘id_customer’, ‘customer_name’, ‘loylap_credit’, ‘sale_amount’
        //        'datetime', type, branch
        params.add("datetime");
        params.add("true");

        String url = POST_MERCHANT_HISTORY;
        for (int i = 0; i < params.size(); i++) {
            url += ("/" + params.get(i));
        }

        return JSONParser.getJSONFromHttpPost(url, null, tag);
    }

    public JSONObject getMerchantHistoryOverview(String branchID, long startDate, long endDate, int tag) {
        List<String> params = new ArrayList<>();
        params.add(branchID);
        params.add(String.valueOf(startDate));
        params.add(String.valueOf(endDate));

        return JSONParser.getJSONFromHttpGet(GET_MERCHANT_HISTORY_OVERVIEW, params, tag);
    }

    public JSONObject sendDigitalGift(long amount, String email, String name, String message, String uid, int tag) {
        Map<String, String> params = new HashMap<>();

        params.put("amount", String.valueOf(amount));
        params.put("recipient_email", email);
        params.put("recipient_name", name);
        params.put("message", message);
        params.put("uid", uid);

        return JSONParser.getNonRetryableJSONFromHttpPost(SEND_DIGITAL_GIFT, params, tag);
    }
}


