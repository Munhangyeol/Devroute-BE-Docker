package com.teamdevroute.devroute.user.dto;

import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.Builder;

public record UserCreateRequest (
       String email,
       String password,
       String name,
       DevelopField development_field,
       String loginType
) {

    @Builder
    public UserCreateRequest(String email, String password, String name, DevelopField development_field, String loginType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.development_field = development_field;
        this.loginType = loginType;
    }

    public User toEntity(String loginType){
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .developField(development_field)
                .loginType(loginType)
                .build();
    }
}
