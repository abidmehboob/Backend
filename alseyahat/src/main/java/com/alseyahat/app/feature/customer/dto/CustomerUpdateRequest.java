package com.alseyahat.app.feature.customer.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerUpdateRequest {

    @NotEmpty
    String name;

    @NotEmpty
    String email;

    String deviceType;

    String channelId;

    String deviceModel;

    String phoneNumber;

    String deviceId;
    
    String cnic;
    
    String password;
    

    List<ShippingAddressUpdateRequest> shippingAddress = new ArrayList<>();


}
