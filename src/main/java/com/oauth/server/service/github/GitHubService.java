package com.oauth.server.service.github;

import com.oauth.server.dto.auth.AccessTokenRequest;
import com.oauth.server.dto.auth.AccessTokenResponse;
import com.oauth.server.dto.auth.UserDto;
import com.oauth.server.exception.GitHubException;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

// GitHubService의 역할
// 1. AccessToken을 요청
// 2. AccessToken으로 유저정보 탐색

public abstract class GitHubService {
    private static final String GITHUB_ACCESS_TOKEN_URI = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_URI = "https://api.github.com/user";
    private static RestTemplate restTemplate = new RestTemplate();

    abstract String getClientId();

    abstract String getClientSecret();

    // 이 코드는 코드를 통해 서비스 제공업자(GitHub)에게 accessToken을 요청하는 함수
    public AccessTokenResponse getAccessToken(String code){
        RequestEntity<AccessTokenRequest> request = RequestEntity
                .post(GITHUB_ACCESS_TOKEN_URI)
                .header("Accept", "application/json")
                .body(new AccessTokenRequest(getClientId(), getClientSecret(), code)); // 리다이렉트 정보가 없어도 되나?
        try{
            return restTemplate
                    .exchange(request, AccessTokenResponse.class)
                    .getBody();
        } catch (Exception e){
            throw new GitHubException("Access Token 획득 실패"+e.getMessage());
        }

    }

    public UserDto getUser(String accessToken){
        RequestEntity<Void> request = RequestEntity
                .get(GITHUB_USER_URI)
                .header("Accept", "application/json")
                .header("Authorization", "token "+accessToken)
                .build();
        try{
            return restTemplate
                    .exchange(request, UserDto.class)
                    .getBody();
        } catch (Exception e){
            throw new GitHubException("유저 정보 획득 실패");
        }

    }



}
