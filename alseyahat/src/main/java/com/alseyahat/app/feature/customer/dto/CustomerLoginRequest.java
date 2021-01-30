package com.alseyahat.app.feature.customer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerLoginRequest {

    
    String name;

    @Email
    String email;

    @NotEmpty
    String fcmToken;

    String deviceType;

    String channelId;

    String deviceModel;

    @NotEmpty
    @Size(min = 9, max = 11)
    @Digits(fraction = 0, integer = 11)
    String phone;

    String deviceId;

    String deviceToken;
    
    String personalKey;

}
