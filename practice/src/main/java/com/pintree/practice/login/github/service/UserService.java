package com.pintree.practice.login.github.service;

import com.pintree.practice.login.github.domain.auth.User;
import com.pintree.practice.login.github.dto.auth.UserDto;
import com.pintree.practice.login.github.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDto userDto) {
        User user = userDto.toUser();
        userRepository.save(user);
    }
}
