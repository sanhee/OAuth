package com.oauth.server.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.oauth.server.dto.auth.UserDto;

public class JwtUtil {
    private static final String JWT_SECRET = "jwtSecret";
    private static final String JWT_ISSUER = "jwtIssuer"; // jwt 발행자인가? 이거 뭔지 모르겠네
    private static final String USER_LOGIN = "login";
    private static final String USER_NAME = "name";

    private JwtUtil(){}

    public static String createJwt(UserDto user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withClaim(USER_LOGIN, user.getLogin())
                    .withClaim(USER_NAME, user.getName())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new JWTCreationException("Jwt 생성 실패", exception.getCause());
        }
    }

    public static UserDto decodeJwt(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            String login = jwt.getClaim(USER_LOGIN).asString();
            String name = jwt.getClaim(USER_NAME).asString();
            return new UserDto(login, name);
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("잘못된 JWT 입니다.");
        }

    }


}
