package com.teamdevroute.devroute.user.dto;

import lombok.Builder;

public record UserAuthResponse(
        String email,
        String name
) {

    @Builder
    public UserAuthResponse(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
