package com.oauth.server.domain.auth;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash("auth")
public class Auth {

    @Id
    private final String userLogin;

    private final User user;

    private final Token token;

    public Auth(String userLogin, User user, Token token) {
        this.userLogin = userLogin;
        this.user = user;
        this.token = token;
    }

    public static Auth from(User user, Token token){
        return new Auth(user.getLogin(), user, token);
    }
}
