package com.alseyahat.app.feature.customer.facade;

import com.alseyahat.app.feature.customer.dto.CustomerDetailResponse;
import com.alseyahat.app.feature.customer.dto.CustomerLoginRequest;
import com.alseyahat.app.feature.customer.dto.CustomerRegisterRequest;
import com.alseyahat.app.feature.customer.dto.CustomerUpdateRequest;
import com.alseyahat.app.feature.customer.dto.CustomerUpdateResponse;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;


public interface CustomerFacade {

    CustomerDetailResponse findOne(final String id);

    ResponseEntity<OAuth2AccessToken> getCustomerToken(final CustomerLoginRequest request);

    ResponseEntity<OAuth2AccessToken> refreshToken(final String refreshToken);

    ResponseEntity<OAuth2AccessToken> registerCustomer(final CustomerRegisterRequest request);

    Page<CustomerDetailResponse> findAllCustomer(final Predicate predicate, final Pageable pageable);

    CustomerUpdateResponse updateCustomer(final String customerId, final CustomerUpdateRequest request);
    
    CustomerDetailResponse findCustomerDetail();

}
