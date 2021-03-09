package com.alseyahat.app.feature.customer.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomerDetailResponse {

    String customerId;

    String email;

    String name;

    String deviceType;

    String channelId;

    String deviceModel;

    String phone;
    
    String cnic;

    String deviceId;

    String deviceToken;

    List<ShippingAddressResponse> shippingAddress;

}

