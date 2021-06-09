package com.oauth.server.dto.auth;

import com.oauth.server.domain.auth.User;

public class UserDto {
    private String login;
    private String name;

    public UserDto() {

    }

    public UserDto(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User toUser() {
        return new User(login, name);
    }

    public static UserDto from(User user){
        return new UserDto(user.getLogin(), user.getName());
    }
}
