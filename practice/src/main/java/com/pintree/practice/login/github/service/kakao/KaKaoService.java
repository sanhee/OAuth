package com.pintree.practice.login.github.service.kakao;

import com.pintree.practice.login.github.dto.auth.kakao.KaKaoAccessTokenResponse;
import com.pintree.practice.login.github.dto.auth.kakao.KaKaoUserProfile;
import com.pintree.practice.login.github.exception.GitHubException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*
    IFNO

    리퀘스트용 DTO를 사용하지 못한 이유:
    - application/x-www-form-urlencoded;charset=utf-8
    - JSON 형태가 아닌 텍스트, 쿼리스트링 형태임 ex) id=노을&name=산희

    Map이 아닌 MultiValueMap을 사용한 이유
    - Spring은 application/x-www-form-urlencoded 형식의 요청을 할 때, 내부적으로 FormHttpMessageConverter 클래스를 사용하는데
    - 이건 MultiValueMap을 지원해서, 일반적인 POJO를 사용할 수 없음

    https://www.baeldung.com/spring-url-encoded-form-data
*/

@Service
public class KaKaoService {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String KAKAO_ACCESS_TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_GET_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    private static final String GRANT_TYPE = "authorization_code";
    private static final String REDIRECT_URI = "http://localhost:8080/kakaoCallback.html";
    private static final String CLIENT_ID = "2136fe5e4dbe63c0dc63dd53d0907401";
    private static final String CLIENT_SECRET = "emNMTR9cdXLh6EauAP5a1zDgF8LuKcZo";
    private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_FORM_URLENCODED;

    public KaKaoAccessTokenResponse getAccessToken(String code) {
        // Request Body 오브젝트 생성
        MultiValueMap<String, String> bodies = new LinkedMultiValueMap<>();
        bodies.add("grant_type", GRANT_TYPE);
        bodies.add("client_id", CLIENT_ID);
        bodies.add("redirect_uri", REDIRECT_URI);
        bodies.add("code", code);
        bodies.add("client_secret", CLIENT_SECRET);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        RequestEntity<MultiValueMap<String, String>> kakaoTokenRequest = RequestEntity
                .post(KAKAO_ACCESS_TOKEN_URI) // status line
                .contentType(CONTENT_TYPE) // header
                .body(bodies);

        // Http 요청하고 response 응답 리턴
        return restTemplate.exchange(
                kakaoTokenRequest,
                KaKaoAccessTokenResponse.class
        ).getBody();
    }

    public KaKaoUserProfile getUser(String accessToken) {
        // Request Body 오브젝트 생성
        MultiValueMap<String, String> bodies = new LinkedMultiValueMap<>();

        //TODO. 멀티밸류인데 왜 따로따로 add 해서 하는건 파싱을 정상적으로 못하는지 아직 잘모르겠음. 다음에 트러블슈팅 해보자!
        bodies.add("property_keys", "[\"properties.nickname\", \"kakao_account.email\"]");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        RequestEntity<MultiValueMap<String, String>> kakaoInfoRequest = RequestEntity
                .post(KAKAO_GET_USER_INFO_URI) // status line
                .header("Authorization", "Bearer " + accessToken)
                .contentType(CONTENT_TYPE) // header
                .body(bodies);

        // Http 요청하고 response 응답 리턴
        ResponseEntity<KaKaoUserProfile> response = restTemplate.exchange(
                kakaoInfoRequest,
                KaKaoUserProfile.class
        );

        try {
            return response.getBody();
        } catch (Exception e) {
            throw new GitHubException("유저 정보 획득 실패");
        }
    }
}
