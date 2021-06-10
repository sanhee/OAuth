package com.oauth.server.repository;

import com.oauth.server.domain.auth.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// 이것이 바로 제어의 역전의 예
// 스프링이 알아서 구현해줌
// Auth는 Redis 저장소로 구현돼있음.  @RedisHash("auth")

@Repository
public interface AuthRepository extends CrudRepository<Auth, String> {
}
