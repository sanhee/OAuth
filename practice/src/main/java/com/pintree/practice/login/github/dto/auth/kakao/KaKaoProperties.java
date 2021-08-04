package com.pintree.practice.login.github.dto.auth.kakao;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class KaKaoProperties {

    private String nickName;

    public KaKaoProperties() {
    }

    @JsonGetter("nickname")
    public String getNickName() {
        return nickName;
    }

    @JsonSetter("nickname")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "KaKaoProperties{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
