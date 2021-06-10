package com.oauth.server.service.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


// GitHubSevice 를 추상 클래스로 선언한 이유
// 중복을 감안하고 가독성과 유지보수를 위해 GitHubIosSevice, GitHubWebSevice로 분리하기 위해서임.

@Service
public class GitHubWebService extends GitHubService{
    @Value("${GITHUB.WEB.CLIENT.ID}")
    private String CLIENT_ID;

    @Value("${GITHUB.WEB.CLIENT.SECRET}")
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
