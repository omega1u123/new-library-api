package org.example.libraryApi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.security.UserRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(@NonNull UserDetails user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusDays(jwtProperties.getAccessTokenExpirationDays()).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(accessExpiration)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretAccess())))
                .claim("roles", UserRoles.USER)
                .compact();
    }


    public String generateRefreshToken(@NonNull UserDetails user) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant refreshExpirationInstant = now.plusDays(jwtProperties.getRefreshTokenExpirationDays()).atZone(ZoneId.systemDefault()).toInstant();
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(refreshExpiration)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretRefresh())))
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretAccess())));
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretRefresh())));
    }

    private boolean validateToken(@NonNull String token, @NonNull Key secret) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }


    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretAccess())));
    }

    public Claims getRefreshClaims(@NonNull String token) {
        return getClaims(token, Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecretRefresh())));
    }

    private Claims getClaims(@NonNull String token, @NonNull Key secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
