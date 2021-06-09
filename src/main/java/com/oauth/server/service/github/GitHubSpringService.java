package com.oauth.server.service.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


// GitHubSevice 를 추상 클래스로 선언한 이유는 뭘까?

@Service
public class GitHubSpringService extends GitHubService{
    @Value("${GITHUB.CLIENT.ID}")
    private String CLIENT_ID;

    @Value("${GITHUB.CLIENT.SECRET}")
    private String CLIENT_SECRET;

    @Override
    String getClientId() {
        return CLIENT_ID;
    }

    @Override
    String getClientSecret() {
        return CLIENT_SECRET;
    }

}
