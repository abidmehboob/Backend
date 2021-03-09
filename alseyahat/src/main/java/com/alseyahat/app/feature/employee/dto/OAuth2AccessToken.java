package com.alseyahat.app.feature.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OAuth2AccessToken {


    @JsonProperty("Bearer")
    String bearer;

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("expires_in")
    int expiresIn;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("scope")
    Set<String> scope;

}
