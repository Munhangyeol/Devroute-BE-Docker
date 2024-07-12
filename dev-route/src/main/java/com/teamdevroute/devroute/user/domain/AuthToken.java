package com.teamdevroute.devroute.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime expired_date;

    @Builder
    public AuthToken(String accessToken, String refreshToken, LocalDateTime expired_date) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expired_date = expired_date;
    }
}
