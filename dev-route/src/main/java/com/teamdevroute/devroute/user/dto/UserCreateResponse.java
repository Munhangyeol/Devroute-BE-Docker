package com.teamdevroute.devroute.user.dto;

import com.teamdevroute.devroute.user.domain.User;
import lombok.Builder;

public record UserCreateResponse (
    Long id,
    String email,
    String name
) {

    @Builder
    public UserCreateResponse(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public static UserCreateResponse of(User user) {
        return UserCreateResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName()).build();
    }
}
