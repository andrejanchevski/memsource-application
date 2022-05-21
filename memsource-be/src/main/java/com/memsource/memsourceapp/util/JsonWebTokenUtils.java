package com.memsource.memsourceapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.memsource.memsourceapp.domain.response.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JsonWebTokenUtils {

    @Value("${application.authentication.jwtsecret}")
    private String jwtSecret;

    public String generateAccessToken(UserResponse userResponse,
                               String issuer,
                               List<String> grantedAuthorities){
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret.getBytes());
        return JWT.create().withSubject(userResponse.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000))
                .withIssuer(issuer)
                .withClaim("roles", grantedAuthorities)
                .withClaim("memsourceApiToken", userResponse.getAuthenticationToken())
                .sign(algorithm);
    }

    public String generateRefreshToken(String userName,
                                String issuer){
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret);
        return JWT.create().withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + 200 * 60 * 1000))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public DecodedJWT decodedJWT(String authorizationHeader){
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256(this.jwtSecret.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }

}
