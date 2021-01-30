package com.alseyahat.app.feature.customer.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ShippingAddressUpdateRequest {

    Long addressId;

    String address;
    
    String city;
    
    String town;
    
    String postcode;
    
    Double latitude;

    Double longitude;

}

