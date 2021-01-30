package com.alseyahat.app.feature.employee.facade;


import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import com.alseyahat.app.feature.employee.dto.EmployeeLoginRequest;
import com.alseyahat.app.feature.employee.dto.SignUpRequest;
import com.alseyahat.app.feature.employee.dto.SignUpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface EmployeeAuthFacade {

    SignUpResponse createEmployee(final SignUpRequest request);

    ResponseEntity<OAuth2AccessToken> refreshToken(final String refreshToken);

    ResponseEntity<Map<String, Object>> employeeLogin(final EmployeeLoginRequest request,
                                                      final HttpServletRequest httpRequest,
                                                      final HttpServletResponse httpResponse);

}