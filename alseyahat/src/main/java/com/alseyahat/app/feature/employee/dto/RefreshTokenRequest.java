package com.alseyahat.app.feature.employee.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RefreshTokenRequest {

    @NotEmpty
    String token;
}