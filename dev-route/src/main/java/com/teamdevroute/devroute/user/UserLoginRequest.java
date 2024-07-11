package com.teamdevroute.devroute.user;

public record UserLoginRequest(
        String email,
        String password
) {

}
