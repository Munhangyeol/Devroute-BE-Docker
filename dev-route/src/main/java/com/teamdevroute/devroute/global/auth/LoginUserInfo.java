package com.teamdevroute.devroute.global.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoginUserInfo {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String role;
}
