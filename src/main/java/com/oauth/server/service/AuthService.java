package com.oauth.server.service;

import com.oauth.server.domain.auth.Auth;
import com.oauth.server.dto.auth.AccessTokenResponse;
import com.oauth.server.dto.auth.UserDto;
import com.oauth.server.exception.AuthenticationException;
import com.oauth.server.repository.AuthRepository;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void save(UserDto userDto, AccessTokenResponse accessTokenResponse){
        Auth auth = Auth.from(userDto.toUser(), accessTokenResponse.toToken());
        authRepository.save(auth);
    }

    public void authenticate(UserDto userDto){
        authRepository.findById(userDto.getLogin()).orElseThrow(()-> new AuthenticationException("로그인하지 않은 유저입니다."));
    }

}
