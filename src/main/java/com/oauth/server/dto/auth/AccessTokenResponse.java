package com.oauth.server.dto.auth;

import com.oauth.server.domain.auth.Token;

public class AccessTokenResponse {
    private String accessToken;
    private String tokenType; // 이거 뭐였지?, 알고리즘 정하는거였나?? 나중에 알아보자
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Token toToken(){
        return new Token(accessToken, tokenType, scope);
    }
}
