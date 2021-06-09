package com.oauth.server.dto.auth;

public class AccessTokenRequest {
    private final String clientId;
    private final String clientSecret;
    private final String code;

    public AccessTokenRequest(String clientId, String clientSecret, String code){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCode() {
        return code;
    }
}
