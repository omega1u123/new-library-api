package org.example.libraryApi.security;

public record JwtRequest(
        String username,
        String password
) {
}

