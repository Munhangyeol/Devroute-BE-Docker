package com.teamdevroute.devroute.global.auth.jwt;

import com.teamdevroute.devroute.global.auth.AuthorizationProvider;
import com.teamdevroute.devroute.global.auth.UserAuthContext;
import com.teamdevroute.devroute.global.auth.UserCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils implements AuthorizationProvider {

    private static final String USER_NAME = "name";
    private static final String USER_ROLE = "role";

    private final String secretKey;
    private final Long expirationMilliSec;

    public JwtUtils(@Value("${devroute.auth.jwt.secret}") String secretKey,
                    @Value("${devroute.auth.jwt.expiration}") Long expirationMilliSec
    ) {
        this.secretKey = secretKey;
        this.expirationMilliSec = expirationMilliSec;
    }

    @Override
    public UserCredential create(UserAuthContext context) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationMilliSec);

        String tokenValue = Jwts.builder()
                .claim(USER_NAME, context.name())
                .claim(USER_ROLE, context.role())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        return new UserCredential(tokenValue);
    }

    @Override
    public UserAuthContext parseCredential(UserCredential token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey)))
                .build()
                .parseClaimsJws(token.authorization())
                .getBody();
        return new UserAuthContext(
                claims.get(USER_NAME, String.class),
                claims.get(USER_ROLE, String.class)
        );
    }
}
