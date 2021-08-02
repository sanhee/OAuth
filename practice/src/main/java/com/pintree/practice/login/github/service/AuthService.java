package com.pintree.practice.login.github.service;

import com.pintree.practice.login.github.domain.auth.Auth;
import com.pintree.practice.login.github.dto.auth.AccessTokenResponse;
import com.pintree.practice.login.github.dto.auth.UserDto;
import com.pintree.practice.login.github.exception.AuthenticationException;
import com.pintree.practice.login.github.repository.AuthRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void save(UserDto userDto, AccessTokenResponse accessTokenResponse) {
        Auth auth = Auth.from(userDto.toUser(), accessTokenResponse.toToken());
        authRepository.save(auth);
    }

    public UserDto getUser(String userId) {
        return UserDto.from(authRepository.findById(userId)
                .orElseThrow(() -> new AuthenticationException("로그인하지 않은 유저입니다."))
                .getUser());
    }
}
