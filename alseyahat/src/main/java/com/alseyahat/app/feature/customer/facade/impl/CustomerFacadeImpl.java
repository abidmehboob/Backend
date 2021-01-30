package com.alseyahat.app.feature.customer.facade.impl;

import com.alseyahat.app.commons.AppUtils;
import com.alseyahat.app.feature.customer.dto.CustomerDetailResponse;
import com.alseyahat.app.feature.customer.dto.CustomerLoginRequest;
import com.alseyahat.app.feature.customer.dto.CustomerRegisterRequest;
import com.alseyahat.app.feature.customer.dto.CustomerShippingAddressCreateRequest;
import com.alseyahat.app.feature.customer.dto.CustomerUpdateRequest;
import com.alseyahat.app.feature.customer.dto.CustomerUpdateResponse;
import com.alseyahat.app.feature.customer.dto.ShippingAddressResponse;
import com.alseyahat.app.feature.customer.dto.ShippingAddressUpdateRequest;
import com.alseyahat.app.feature.customer.facade.CustomerFacade;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.customer.repository.entity.QCustomer;
import com.alseyahat.app.feature.customer.repository.entity.QShippingAddress;
import com.alseyahat.app.feature.customer.repository.entity.ShippingAddress;
import com.alseyahat.app.feature.customer.service.CustomerService;
import com.alseyahat.app.feature.customer.service.ShippingAddressService;
import com.alseyahat.app.security.SecurityConstant;
import com.alseyahat.app.security.config.JwtConfigProperties;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerFacadeImpl implements CustomerFacade {

    ModelMapper modelMapper;

    MessageSource messageSource;

    TokenEndpoint tokenEndpoint;

    CustomerService customerService;

    JwtConfigProperties jwtConfigProperties;

    ShippingAddressService shippingAddressService;

    AuthenticationManager authenticationManager;

   
    @Override
    public CustomerDetailResponse findOne(final String id) {
        return buildCustomerDetailResponse(customerService.findOne(QCustomer.customer.customerId.eq(id)));
    }

    @Override
    public CustomerDetailResponse findCustomerDetail() {
        String username = AppUtils.getUserNameFromAuthentication();
        return buildCustomerDetailResponse(customerService.findOne(QCustomer.customer.personalKey.eq(username)));
    }

    @Override
    public ResponseEntity<OAuth2AccessToken> registerCustomer(final CustomerRegisterRequest request) {
        log.trace("Creating customer [{}]", request);
        ResponseEntity<OAuth2AccessToken> response = null;
          if (StringUtils.isNotEmpty(request.getPersonalKey()) && customerService.exist(QCustomer.customer.personalKey.eq(request.getPersonalKey()))) {
                log.trace("Get customer token with phone [{}]", request.getPersonalKey());
                CustomerUpdateResponse customerUpdateResponse = updateCustomer(customerService.findOne(QCustomer.customer.personalKey.eq(request.getPersonalKey())).getCustomerId(), buildUpdate(request));
                log.trace("customer updated with id [{}]", customerUpdateResponse.getId());
                return getCustomerToken(buildCustomerLoginRequest(request));
            } else {
                final Customer savedCustomer = customerService.save(build(request));
//                sendCreateCustomerNotification(savedCustomer.getName(), savedCustomer.getEmail());
                log.trace("Customer created with id [{}]", savedCustomer.getCustomerId());
                response = getCustomerToken(buildCustomerLoginRequest(request));
                log.trace("Get Customer token after creation with email [{}]", savedCustomer.getEmail());
                return response;
            }
     
        }


    @Override
    @SneakyThrows
    public ResponseEntity<OAuth2AccessToken> getCustomerToken(final CustomerLoginRequest request) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getPersonalKey(), request.getPhone(), Collections.emptyList()));
        final User userPrincipal = new User(jwtConfigProperties.getCustomer().getClientId(), jwtConfigProperties.getCustomer().getClientSecret(),
                true, true, true, true, Collections.emptyList());
        final ResponseEntity<OAuth2AccessToken> response = tokenEndpoint.postAccessToken(
                new UsernamePasswordAuthenticationToken(userPrincipal,
                        jwtConfigProperties.getCustomer().getClientSecret(), Collections.emptyList()), buildLoginParams(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return response;
    }

    @Override
    @SneakyThrows
    public ResponseEntity<OAuth2AccessToken> refreshToken(final String refreshToken) {
        final User userPrincipal = new User(jwtConfigProperties.getCustomer().getClientId(), jwtConfigProperties.getCustomer().getClientSecret(),
                true, true, true, true, Collections.emptyList());
        return tokenEndpoint.postAccessToken(new UsernamePasswordAuthenticationToken(userPrincipal,
                jwtConfigProperties.getCustomer().getClientSecret(), Collections.emptyList()), buildRefreshTokenParams(refreshToken));
    }

    @Override
    public Page<CustomerDetailResponse> findAllCustomer(final Predicate predicate, final Pageable pageable) {
        log.trace("Finding customers predicate [{}]", predicate);
        return customerService.findAll(predicate, pageable).map(this::buildCustomerDetailResponse);
    }

    @Override
    public CustomerUpdateResponse updateCustomer(final String customerId, final CustomerUpdateRequest request) {
        log.trace("Updating customer [{}]", request);
        final Customer customer = customerService.findOne(QCustomer.customer.customerId.eq(customerId));
        final Customer updatedCustomer = customerService.save(build(customer, request));
        log.trace("customer updated with id [{}]", updatedCustomer.getCustomerId());
        return buildCustomerUpdateResponse(updatedCustomer);
    }

//    private void sendCreateCustomerNotification(final String name, final String email) {
//        try {
//            final EmailDto emailDto = new EmailDto();
//            emailDto.setFrom(messageSource.getMessage("customer.email.register.from", null, LocaleContextHolder.getLocale()));
//            emailDto.setTo(new String[]{email});
//            final Map<String, Object> content = new HashMap<>();
//            content.put("name", name);
//            emailDto.setContent(content);
//            emailDto.setSubject(messageSource.getMessage("customer.email.register.subject", null, LocaleContextHolder.getLocale()));
////            emailDtoNotificationService.prepareAndSendMessage(emailDto, "email", Locale.forLanguageTag("en"), "/customer-create-email-template.ftl");
//            log.trace("Email sent to email [{}]", email);
//        } catch (final Exception ex) {
//            log.trace("Unable to send email to recipient [{}] due to [{}]", email, ex);
//        }
//    }

    private Map<String, String> buildLoginParams(final CustomerLoginRequest request) {
        final Map<String, String> params = new HashMap<>();
        params.put(SecurityConstant.SCOPE, "read write trust");
        params.put(SecurityConstant.GRANT_TYPE, SecurityConstant.PASSWORD);
        params.put(SecurityConstant.USERNAME, request.getPersonalKey());
        params.put(SecurityConstant.PASSWORD, request.getPhone());
        params.put(SecurityConstant.CLIENT_ID, jwtConfigProperties.getCustomer().getClientId());
        params.put(SecurityConstant.CLIENT_SECRET, jwtConfigProperties.getCustomer().getClientSecret());
        return params;
    }

    private Customer build(final CustomerRegisterRequest request) {
        Customer customer = new Customer();
        modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
        TypeMap<CustomerRegisterRequest, Customer> typeMap = modelMapper.typeMap(CustomerRegisterRequest.class, Customer.class);
        typeMap.addMappings(mapper -> mapper.skip(Customer::setCustomerId));
        typeMap.addMappings(mapper -> mapper.skip(Customer::setDateCreated));
        typeMap.addMappings(mapper -> mapper.skip(Customer::setLastUpdated));
        typeMap.addMappings(mapper -> mapper.skip(Customer::setShippingAddress));
        typeMap.map(request, customer);
        customer.setShippingAddress(request.getShippingAddress().stream().map(address -> build(customer, address)).collect(Collectors.toList()));
        return customer;

    }

    private Customer build(Customer customer, final CustomerUpdateRequest request) {
        modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
        TypeMap<CustomerUpdateRequest, Customer> typeMap = modelMapper.typeMap(CustomerUpdateRequest.class, Customer.class);
        typeMap.addMappings(mapper -> mapper.skip(Customer::setCustomerId));
        typeMap.addMappings(mapper -> mapper.skip(Customer::setDateCreated));
        typeMap.addMappings(mapper -> mapper.skip(Customer::setLastUpdated));
        typeMap.map(request, customer);
        customer.setShippingAddress(request.getShippingAddress().stream().map(address -> build(customer, address)).collect(Collectors.toList()));
        return customer;
    }

    private ShippingAddress build(final Customer customer, final CustomerShippingAddressCreateRequest request) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setAddress(request.getAddress());
        shippingAddress.setLatitude(request.getLatitude());
        shippingAddress.setLongitude(request.getLongitude());
        shippingAddress.setCustomer(customer);
        return shippingAddress;
    }

    private ShippingAddress build(final Customer customer, final ShippingAddressUpdateRequest request) {
        ShippingAddress shippingAddress = shippingAddressService.findOne(QShippingAddress.shippingAddress.addressId.eq(request.getAddressId()));
        shippingAddress.setAddress(request.getAddress());
        shippingAddress.setLatitude(request.getLatitude());
        shippingAddress.setLongitude(request.getLongitude());
        shippingAddress.setCustomer(customer);
        return shippingAddress;
    }

    private CustomerDetailResponse buildCustomerDetailResponse(final Customer customer) {
        CustomerDetailResponse response = new CustomerDetailResponse();
        modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
        TypeMap<Customer, CustomerDetailResponse> typeMap = modelMapper.typeMap(Customer.class, CustomerDetailResponse.class);
        typeMap.addMappings(mapper -> mapper.skip(CustomerDetailResponse::setShippingAddress));
        typeMap.map(customer, response);
        response.setShippingAddress(customer.getShippingAddress().stream().map(this::buildShippingAddress).collect(Collectors.toList()));
        return response;
    }

    private CustomerUpdateResponse buildCustomerUpdateResponse(final Customer customer) {
        CustomerUpdateResponse response = new CustomerUpdateResponse();
        TypeMap<Customer, CustomerUpdateResponse> typeMap = modelMapper.typeMap(Customer.class, CustomerUpdateResponse.class);
        typeMap.map(customer, response);
        return response;
    }

    private ShippingAddressResponse buildShippingAddress(final ShippingAddress entity) {
        ShippingAddressResponse response = new ShippingAddressResponse();
        TypeMap<ShippingAddress, ShippingAddressResponse> typeMap = modelMapper.typeMap(ShippingAddress.class, ShippingAddressResponse.class);
        typeMap.map(entity, response);
        return response;
    }

    private CustomerLoginRequest buildCustomerLoginRequest(final CustomerRegisterRequest request) {
        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
        customerLoginRequest.setDeviceType(request.getDeviceType());
        customerLoginRequest.setEmail(request.getEmail());
        customerLoginRequest.setPhone(request.getPhone());
        customerLoginRequest.setName(request.getName());
        customerLoginRequest.setPersonalKey(request.getPersonalKey());
        customerLoginRequest.setFcmToken(request.getFcmToken());
        return customerLoginRequest;
    }

    private Map<String, String> buildRefreshTokenParams(final String refreshToken) {
        final Map<String, String> params = new HashMap<>();
        params.put(SecurityConstant.GRANT_TYPE, SecurityConstant.REFRESH_TOKEN);
        params.put(SecurityConstant.REFRESH_TOKEN, refreshToken);
        params.put(SecurityConstant.CLIENT_ID, jwtConfigProperties.getCustomer().getClientId());
        params.put(SecurityConstant.CLIENT_SECRET, jwtConfigProperties.getCustomer().getClientSecret());
        return params;
    }

//    private boolean validateAcMob(CustomerRegisterRequest request) {
//        GetVerifyTokenResponse getVerifyTokenResponse = new GetVerifyTokenResponse();
//        try {
//            log.trace("befor acmob validation  PersonalKey [{}]", request.getPersonalKey());
//            getVerifyTokenResponse = acMobFacade.getVerifyToken(buildAcMobRequest(request));
//            log.trace("After acmob validation  Token [{}]", getVerifyTokenResponse.getToken());
//            if (StringUtils.isNotEmpty(getVerifyTokenResponse.getToken()))
//                return true;
//            else
//                return false;
//        } catch (Exception ex) {
//            return false;
//        }
//
//    }

//    private GetVerifyTokenRequest buildAcMobRequest(CustomerRegisterRequest request) {
//        GetVerifyTokenRequest getVerifyTokenRequest = new GetVerifyTokenRequest();
//        getVerifyTokenRequest.setPhoneNumber(request.getPhone());
//        getVerifyTokenRequest.setDeviceID(request.getDeviceId());
//        getVerifyTokenRequest.setDeviceModel(request.getDeviceModel());
//        getVerifyTokenRequest.setTokenID(request.getTokenId());
//        getVerifyTokenRequest.setChannelID(NOTIFICATION_CHANNEL_ID_VALUE);
//        getVerifyTokenRequest.setTransactionDateTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
//        return getVerifyTokenRequest;
//    }

    private CustomerUpdateRequest buildUpdate(final CustomerRegisterRequest request) {
        CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest();
        customerUpdateRequest.setDeviceType(request.getDeviceType());
        customerUpdateRequest.setEmail(request.getEmail());
        customerUpdateRequest.setPhoneNumber(request.getPhone());
        customerUpdateRequest.setName(request.getName());
        customerUpdateRequest.setFcmToken(request.getFcmToken());
        return customerUpdateRequest;
    }

}

