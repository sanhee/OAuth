package com.pintree.practice.login.github.controller;

import com.pintree.practice.login.github.annotation.LoginRequired;
import com.pintree.practice.login.github.dto.auth.AccessTokenResponse;
import com.pintree.practice.login.github.dto.auth.AuthRequest;
import com.pintree.practice.login.github.dto.auth.AuthResponse;
import com.pintree.practice.login.github.dto.auth.UserDto;
import com.pintree.practice.login.github.service.AuthService;
import com.pintree.practice.login.github.service.UserService;
import com.pintree.practice.login.github.service.github.GitHubService;
import com.pintree.practice.login.github.service.github.GitHubSpringService;
import com.pintree.practice.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final GitHubService gitHubService;
    private final UserService userService;
    private final AuthService authService;

    public AuthController(GitHubSpringService gitHubService, UserService userService, AuthService authService) {
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
}
