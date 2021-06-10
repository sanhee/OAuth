package com.oauth.server.dto.auth;

import com.fasterxml.jackson.annotation.JsonGetter;

public class AuthResponse {
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    @JsonGetter("token")
    public String getToken() {
        return token;
    }
}
