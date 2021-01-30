package com.alseyahat.app.security.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtConfigProperties {

    Employee employee;

    Customer customer;

    Security security;

    @Setter
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Employee {
        String clientId;
        String clientSecret;
        String accessTokenValiditySeconds;
        String refreshTokenValiditySeconds;
    }

    @Setter
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Customer {
        String clientId;
        String clientSecret;
        String accessTokenValiditySeconds;
        String refreshTokenValiditySeconds;
    }


    @Setter
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Security {
        Resource keyStore;
        String keyStorePassword;
        String keyPairAlias;
        String keyPairPassword;
    }
}
