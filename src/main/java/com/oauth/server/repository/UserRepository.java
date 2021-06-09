package com.oauth.server.repository;

import com.oauth.server.domain.auth.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user){
        //INFO. 데이터 삽입 시, 중복키 제약조건에 위배 되면 ON DUPLICATE KEY UPDATE 아래에 지정한 필드가 수정된다.
        String sql = "INSERT INTO `user` (`login`, `name`) values (?, ?)"
                + "ON DUPLICATE KEY UPDATE `name` = ?";
        jdbcTemplate.update(sql,user.getLogin(),user.getName(),user.getName());
    }

}
