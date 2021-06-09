package com.oauth.server.service;

import com.oauth.server.dto.auth.UserDto;
import com.oauth.server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDto userDto){
        userRepository.save(userDto.toUser());
    }
}
