package com.oauth.server.dto.auth;

public class AuthRequest {
    private String code;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

}
