package com.pintree.practice.login.github.repository;

import com.pintree.practice.login.github.domain.auth.Auth;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Auth, String> {
}
