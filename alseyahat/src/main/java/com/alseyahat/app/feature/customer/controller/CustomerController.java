package com.alseyahat.app.feature.customer.controller;

import com.alseyahat.app.feature.customer.dto.CustomerDetailResponse;
import com.alseyahat.app.feature.customer.dto.CustomerLoginRequest;
import com.alseyahat.app.feature.customer.dto.CustomerRegisterRequest;
import com.alseyahat.app.feature.customer.dto.CustomerUpdateRequest;
import com.alseyahat.app.feature.customer.dto.CustomerUpdateResponse;
import com.alseyahat.app.feature.customer.facade.CustomerFacade;
import com.alseyahat.app.feature.employee.dto.ChangePasswordRequest;
import com.alseyahat.app.feature.employee.dto.RefreshTokenRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "Customer")
@RequestMapping("/customers")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {

    CustomerFacade customerFacade;

    @GetMapping("/own-detail")
    @ApiOperation(value = "Get customer own detail", nickname = "getCustomerOwnDetail", notes = "Get customer own detail")
    public ResponseEntity<CustomerDetailResponse> getCustomerDetail() {
    	return new ResponseEntity<>(customerFacade.findCustomerDetail(), HttpStatus.OK);
    }
    
 
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get customer login token", nickname = "getCustomerToken", notes = "Get customer login token")
    public ResponseEntity<OAuth2AccessToken> getCustomerToken(@Valid @RequestBody final CustomerLoginRequest request) {
        return customerFacade.getCustomerToken(request);
    }

    @PostMapping(value = "/refresh_token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Refresh Token", nickname = "employeeLogin", notes = "Refresh Token")
    public ResponseEntity<OAuth2AccessToken> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return customerFacade.refreshToken(request.getToken());
    }

    @ApiOperation(value = "Register customer if there is not customer or get token", nickname = "registerCustomer", notes = "Register customer if there is not customer or get token")
    @PostMapping(value = "/register-customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OAuth2AccessToken> registerCustomer(@Valid @RequestBody final CustomerRegisterRequest request) {
        return customerFacade.registerCustomer(request);
    }

    @ApiOperation(value = "Update customer user detail", nickname = "updateCustomerDetails", notes = "Update customer user detail")
    @PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerUpdateResponse> updateCustomerDetails(@PathVariable("customerId") final String customerId,
                                                                        @Valid @RequestBody final CustomerUpdateRequest request) {
        return new ResponseEntity<>(customerFacade.updateCustomer(customerId, request), HttpStatus.OK);
    }
    
    @PostMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change employee password", nickname = "changePassword", notes = "Change employee password")
    public ResponseEntity<Void> changePassword(@RequestBody final ChangePasswordRequest request) {
    	customerFacade.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


