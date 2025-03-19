package org.example.libraryApi.security;

public record JwtResponse(
        String refreshToken,
        String accessToken
) {
}