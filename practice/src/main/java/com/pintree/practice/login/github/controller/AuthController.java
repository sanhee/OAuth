package com.pintree.practice.login.github.controller;

import com.pintree.practice.login.github.annotation.LoginRequired;
import com.pintree.practice.login.github.dto.auth.*;
import com.pintree.practice.login.github.dto.auth.kakao.KaKaoAccessTokenResponse;
import com.pintree.practice.login.github.dto.auth.kakao.KaKaoAuthResponse;
import com.pintree.practice.login.github.dto.auth.kakao.KaKaoUserProfile;
import com.pintree.practice.login.github.service.AuthService;
import com.pintree.practice.login.github.service.UserService;
import com.pintree.practice.login.github.service.github.GitHubService;
import com.pintree.practice.login.github.service.kakao.KaKaoService;
import com.pintree.practice.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final KaKaoService kaKaoService;
    private final GitHubService gitHubService;
    private final UserService userService;
    private final AuthService authService;

    public AuthController(KaKaoService kaKaoService, GitHubService gitHubService, UserService userService, AuthService authService) {
        this.kaKaoService = kaKaoService;
        this.gitHubService = gitHubService;
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/userInfo")
    @LoginRequired
    public UserDto getUser(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        return authService.getUser(userId);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        String code = authRequest.getCode();
        AccessTokenResponse accessTokenResponse = gitHubService.getAccessToken(code);
        String accessToken = accessTokenResponse.getAccessToken();
        UserDto userDto = gitHubService.getUser(accessToken);

        userService.save(userDto);
        authService.save(userDto, accessTokenResponse);

        return ResponseEntity.status(CREATED)
                .body(new AuthResponse(
                        JwtUtil.createJwt(userDto),
                        userDto.getName(),
                        userDto.getAvatarUrl()));
    }

    @PostMapping("/auth/kakao/callback")
    public ResponseEntity<KaKaoAuthResponse> kakaoCallback(@RequestBody AuthRequest authRequest){ // Data를 리턴해주는 컨트롤러함수
        String code = authRequest.getCode();
        KaKaoAccessTokenResponse accessTokenResponse = kaKaoService.getAccessToken(code);
        String accessToken = accessTokenResponse.getAccessToken();

        KaKaoUserProfile userProfile = kaKaoService.getUser(accessToken);

        return null;
    }

}
