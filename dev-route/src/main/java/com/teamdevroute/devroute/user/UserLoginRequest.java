package com.teamdevroute.devroute.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @NotNull(message = "이메일은 null 일 수 없습니다.")
    @Email
    private String email;

    @NotNull(message = "비밀번호는 null 일 수 없습니다.")
    private String password;
}
