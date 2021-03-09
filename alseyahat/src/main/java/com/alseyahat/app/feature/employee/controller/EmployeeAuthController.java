package com.alseyahat.app.feature.employee.controller;

//import com.alseyahat.app.commons.AppUtils;
import com.alseyahat.app.commons.Constants;
//import com.alseyahat.app.feature.employee.dto.EmployeeLoginRequest;
//import com.alseyahat.app.feature.employee.dto.RefreshTokenRequest;
//import com.alseyahat.app.feature.employee.dto.SignUpRequest;
//import com.alseyahat.app.feature.employee.dto.SignUpResponse;
//import com.alseyahat.app.feature.employee.facade.EmployeeAuthFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alseyahat.app.commons.AppUtils;
import com.alseyahat.app.feature.employee.dto.EmployeeLoginRequest;
import com.alseyahat.app.feature.employee.dto.RefreshTokenRequest;
import com.alseyahat.app.feature.employee.dto.SignUpRequest;
import com.alseyahat.app.feature.employee.dto.SignUpResponse;
import com.alseyahat.app.feature.employee.facade.EmployeeAuthFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Api(tags = "Employee Authentication")
@RequestMapping(value = "/employees/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeAuthController {

    EmployeeAuthFacade authEmployeeFacade;

//    @RequiresCaptcha
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Employee login", nickname = "employeeLogin", notes = "Employee login")
    public ResponseEntity<Map<String, Object>> employeeLogin(@Valid @RequestBody final EmployeeLoginRequest request,
                                                             final HttpServletRequest httpRequest,
                                                             final HttpServletResponse httpResponse) {
        return authEmployeeFacade.employeeLogin(request, httpRequest, httpResponse);
    }

    @PostMapping(value = "/refresh_token", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Refresh Token", nickname = "employeeLogin", notes = "Refresh Token")
    public ResponseEntity<OAuth2AccessToken> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return authEmployeeFacade.refreshToken(request.getToken());
    }

    @PostMapping(value = "/register-employee", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register employee user", nickname = "employeeLogin", notes = "Register employee user")
    public ResponseEntity<SignUpResponse> employeeSignUp(@Valid @RequestBody final SignUpRequest request) {
        return new ResponseEntity<>(authEmployeeFacade.createEmployee(request), HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Logout employee", nickname = "logOutEmployee", notes = "Invalidate the employee token")
    @PostMapping(value = "/logout-employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public void logoutEmployee( final HttpServletRequest httpRequest,final HttpServletResponse httpResponse) {
    	  httpResponse.addCookie(AppUtils.removeTokenCookie(Constants.TOKEN, StringUtils.EMPTY));
          httpResponse.addCookie(AppUtils.removeTokenCookie(Constants.REFRESH_TOKEN, StringUtils.EMPTY));
    }
}
