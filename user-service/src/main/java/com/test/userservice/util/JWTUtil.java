package com.test.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.test.userservice.users.domain.User;

import java.util.Date;

public class JWTUtil {

    private static final String SECRET_KEY = "hoowave";
    private static final int EXPIRATION_TIME_SECONDS = 3600;

    public static String generate(User user) {
        Date now = new Date();
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(now.getTime() + EXPIRATION_TIME_SECONDS * 1000))
                .withIssuedAt(now)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }
}
