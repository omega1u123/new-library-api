package org.example.libraryApi.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.libraryApi.security.service.AuthService;
import org.example.libraryApi.security.JwtRequest;
import org.example.libraryApi.security.RefreshJwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signIn")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) {
        log.info("signIn controller");
        var response = authService.authenticate(jwtRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/refreshAccessToken")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshJwtRequest refreshToken) {
        var response = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(response);
    }

}
