package com.oauth.server.dto.auth;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.oauth.server.domain.auth.Token;

public class AccessTokenResponse {
    private String accessToken;
    private String tokenType; // 이거 뭐였지?, 알고리즘 정하는거였나?? 나중에 알아보자
    private String scope;

    @JsonGetter("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonGetter("token_type")
    public String getTokenType() {
        return tokenType;
    }

    @JsonGetter("scope")
    public String getScope() {
        return scope;
    }

    @JsonSetter("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonSetter("token_type")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonSetter("scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    public Token toToken(){
        return new Token(accessToken, tokenType, scope);
    }
}
