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
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerRegisterRequest {

//    @NotEmpty(message = "error.customer.name_required")
    String name;

    @Email
    String email;
 
    String deviceType;

    String deviceModel;
    
    @Size(min = 9, max = 11)
    @Digits(fraction = 0, integer = 11)
    @NotEmpty
    String phone;

    String deviceId;

    String personalKey;
 

    List<CustomerShippingAddressCreateRequest> shippingAddress = new ArrayList<>();
}
