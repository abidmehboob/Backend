package com.alseyahat.app.feature.customer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerLoginRequest {

    
    String name;
  
    String email;
   
    String deviceType;

    String deviceModel;

    @NotEmpty
    @Size(min = 9, max = 11)
    @Digits(fraction = 0, integer = 11)
    String phone;

    String deviceId;

    @NotEmpty
    String personalKey;

}
