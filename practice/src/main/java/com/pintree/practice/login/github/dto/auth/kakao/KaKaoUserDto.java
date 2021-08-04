package com.pintree.practice.login.github.dto.auth.kakao;

import com.pintree.practice.login.github.domain.auth.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KaKaoUserDto {
    private final String name;
    private final String emails;

    public KaKaoUserDto(String name, String emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public String getEmails() {
        return emails;
    }
}
