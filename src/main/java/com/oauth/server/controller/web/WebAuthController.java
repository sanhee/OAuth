package com.oauth.server.controller.web;

import com.oauth.server.annotation.LoginRequired;
import com.oauth.server.annotation.UserAttribute;
import com.oauth.server.dto.MessageResponse;
import com.oauth.server.dto.auth.AccessTokenResponse;
import com.oauth.server.dto.auth.AuthRequest;
import com.oauth.server.dto.auth.AuthResponse;
import com.oauth.server.dto.auth.UserDto;
import com.oauth.server.service.AuthService;
import com.oauth.server.service.UserService;
import com.oauth.server.service.github.GitHubService;
import com.oauth.server.service.github.GitHubWebService;
import com.oauth.server.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/web")
public class WebAuthController {
    private final GitHubService gitHubService;
    private final UserService userService;
    private final AuthService authService;

    public WebAuthController(GitHubWebService gitHubService, UserService userService, AuthService authService) {
        this.gitHubService = gitHubService;
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/hello")
    @LoginRequired
    public MessageResponse getHello(@UserAttribute UserDto user) {
        authService.authenticate(user);
        return new MessageResponse("안녕하세요, " + user.getName() + " 님!\n 로그인 한 유저는 언제나 환영입니다!");
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest authRequest) {
        String code = authRequest.getCode();

        AccessTokenResponse accessTokenResponse = gitHubService.getAccessToken(code);
        String accessToken = accessTokenResponse.getAccessToken();

        UserDto userDto = gitHubService.getUser(accessToken);
        userService.save(userDto);
        authService.save(userDto, accessTokenResponse); // 이부분 이해 못함

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(JwtUtil.createJwt(userDto)));
    }


}
