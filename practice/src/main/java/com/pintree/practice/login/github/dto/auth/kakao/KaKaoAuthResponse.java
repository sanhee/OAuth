package com.pintree.practice.login.github.dto.auth.kakao;

public class KaKaoAuthResponse {
    private final String token;
    private final String name;

    public KaKaoAuthResponse(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

}
