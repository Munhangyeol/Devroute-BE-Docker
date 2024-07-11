package com.teamdevroute.devroute.global.auth;

public interface AuthorizationProvider {

    UserCredential create(UserAuthContext user);

    UserAuthContext parseCredential(UserCredential token);
}
