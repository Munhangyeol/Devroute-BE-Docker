package com.teamdevroute.devroute.global.auth;

import io.jsonwebtoken.Claims;

public interface AuthorizationProvider {

    String create(LoginUserInfo context);

    Claims parseClaims(String token);

    boolean validateToken(String token);

    public Long getUserId(String token);
}
