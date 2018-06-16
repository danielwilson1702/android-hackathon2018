package com.amplify.requests;


public interface OnTaskCompleted {
    enum Result {SUCCESS, FAILURE, NO_CLIENT_CONNECTION, NETWORK_ERROR, NO_RESPONSE, CANCELLED}
}
