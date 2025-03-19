package org.example.libraryApi.util;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "jwt")
@ConfigurationPropertiesScan
@Getter
@Setter
@Slf4j
public class JwtProperties {

    private String secretAccess;
    private String secretRefresh;
    private int accessTokenExpirationDays;
    private int refreshTokenExpirationDays;

    @PostConstruct
    public void init() {
        log.info("Secret Access: {}", secretAccess);
        log.info("Secret Refresh: {}", secretRefresh);
        log.info("Access Token Expiration Days: {}", accessTokenExpirationDays);
        log.info("Refresh Token Expiration Days: {}", refreshTokenExpirationDays);
    }


}
