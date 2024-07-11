package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.user.domain.User;

public class UserFixture {

    public static User userWithName(String name) {
        return User.builder()
                .email("email")
                .password("password")
                .name(name)
                .developField("AI")
                .build();
    }
}
