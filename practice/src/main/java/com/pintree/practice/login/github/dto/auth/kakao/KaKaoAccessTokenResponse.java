package com.pintree.practice.login.github.dto.auth.kakao;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pintree.practice.login.github.domain.auth.Token;

public class KaKaoAccessTokenResponse {

    private String tokenType;
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String refreshTokenExpiresIn;
    private String scope;

    @JsonGetter("token_type")
    public String getTokenType() {
        return tokenType;
    }

    @JsonSetter("token_type")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonGetter("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonSetter("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonGetter("expires_in")
    public String getExpiresIn() {
        return expiresIn;
    }

    @JsonGetter("expires_in")
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonGetter("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    @JsonSetter("refresh_token")
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @JsonGetter("refresh_token_expires_in")
    public String getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    @JsonSetter("refresh_token_expires_in")
    public void setRefreshTokenExpiresIn(String refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    @JsonGetter("scope")
    public String getScope() {
        return scope;
    }

    @JsonSetter("scope")
    public void setScope(String scope) {
        this.scope = scope;
    }

    public Token toToken() {
        return new Token(accessToken, tokenType, scope);
    }
}
