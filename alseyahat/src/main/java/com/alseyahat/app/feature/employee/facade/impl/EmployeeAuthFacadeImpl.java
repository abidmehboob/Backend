package com.alseyahat.app.feature.employee.facade.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.alseyahat.app.feature.employee.dto.EmployeeLoginRequest;
import com.alseyahat.app.feature.employee.dto.SignUpRequest;
import com.alseyahat.app.feature.employee.dto.SignUpResponse;
import com.alseyahat.app.feature.employee.facade.EmployeeAuthFacade;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.alseyahat.app.feature.employee.repository.entity.EmployeeRole;
import com.alseyahat.app.feature.employee.repository.entity.QEmployeeRole;
import com.alseyahat.app.feature.employee.service.EmployeeRoleService;
import com.alseyahat.app.feature.employee.service.EmployeeService;
import com.alseyahat.app.security.config.JwtConfigProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static com.alseyahat.app.security.SecurityConstant.CLIENT_ID;
import static com.alseyahat.app.security.SecurityConstant.CLIENT_SECRET;
import static com.alseyahat.app.security.SecurityConstant.GRANT_TYPE;
import static com.alseyahat.app.security.SecurityConstant.PASSWORD;
import static com.alseyahat.app.security.SecurityConstant.REFRESH_TOKEN;
import static com.alseyahat.app.security.SecurityConstant.SCOPE;
import static com.alseyahat.app.security.SecurityConstant.USERNAME;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeAuthFacadeImpl implements EmployeeAuthFacade {

    ModelMapper modelMapper;

    PasswordEncoder encoder;

    TokenEndpoint tokenEndpoint;

    EmployeeService employeeService;

    JwtConfigProperties jwtConfigProperties;

    AuthenticationManager authenticationManager;

    EmployeeRoleService employeeRoleService;
    
//    BankNotificationService bankNotificationService;
    
//    EmployeeBrachRolesService employeeBrachRolesService;
  
    
    @Override
    public SignUpResponse createEmployee(final SignUpRequest request) {
        log.trace("Creating employee with email [{}]", request.getEmail());
//        final String username = AppUtils.getUserNameFromAuthentication();
//        final Optional<Employee> loginEmployee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
//        if (!loginEmployee.isPresent()) {
//            List<EmployeeBranchRoles> employeeRoleLst = employeeBrachRolesService.findAll(QEmployeeBranchRoles.employeeBranchRoles.employee.employeeId.eq(loginEmployee.get().getEmployeeId()), Pageable.unpaged()).getContent();
//           
//            if(employeeRoleLst.stream().anyMatch(role -> BRANCH_ADMIN.equals(role.getRole().getName()) && TYPE_TWO.equals(role.getRole().getType()))) {
//	           	 Employee employee = new Employee();
//                 employee.setEmail(request.getEmail());
//                 employee.setName(request.getName());
//                 employee.setPhone(request.getPhone());
//                 employee.setPassword(encoder.encode(request.getPassword()));
//                 final Employee savedEmployee = employeeService.save(employee);
//                 sendBankNotification(request, request.getPassword());
//                 log.trace("Employee created with id [{}]", savedEmployee.getEmployeeId());
//                 return buildCustomerRegisterResponse(savedEmployee);
//	        }else if(employeeRoleLst.stream().anyMatch(role -> STORE_SUPER_ADMIN.equals(role.getRole().getName()) && TYPE_ONE.equals(role.getRole().getType()))) {
//	        	 Employee employee = new Employee();
//	             employee.setEmail(request.getEmail());
//	             employee.setName(request.getName());
//	             employee.setPhone(request.getPhone());
//	             employee.setPassword(encoder.encode(request.getPassword()));
//	             final Employee savedEmployee = employeeService.save(employee);
//	             sendBankNotification(request, request.getPassword());
//	             log.trace("Employee created with id [{}]", savedEmployee.getEmployeeId());
//	             return buildCustomerRegisterResponse(savedEmployee);
//	       }else if(employeeRoleLst.stream().anyMatch(role -> (BANK_ADMIN.equals(role.getRole().getName()) || BANK_SUPER_ADMIN.equals(role.getRole().getName())) && TYPE_ZERO.equals(role.getRole().getType()))) {
	    	     Employee employee = new Employee();
	             employee.setEmail(request.getEmail());
	             employee.setName(request.getName());
	             employee.setPhone(request.getPhone());
	             employee.setPassword(encoder.encode(request.getPassword()));
	             EmployeeRole employeeRole = employeeRoleService.findOne(QEmployeeRole.employeeRole.roleId.eq(Long.valueOf(request.getRoleId())));
//	             List<EmployeeRole> roles= new ArrayList<EmployeeRole>();
//	             roles.add(employeeRole);
//	             employee.setRoles(roles);
	             employee.setRoleId(request.getRoleId());
	             final Employee savedEmployee = employeeService.save(employee);
//	             sendBankNotification(request, request.getPassword());
//	             log.trace("Employee created with id [{}]", savedEmployee.getEmployeeId());
	             return buildCustomerRegisterResponse(savedEmployee);
//		   }else
//			   throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
//        }else
//        	throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
       
    }

    @Override
    @SneakyThrows
    public ResponseEntity<Map<String, Object>> employeeLogin(final EmployeeLoginRequest request,
                                                             final HttpServletRequest httpRequest,
                                                             final HttpServletResponse httpResponse) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail() == null ? request.getPhone() : request.getEmail(), request.getPassword(), Collections.emptyList()));

        final User userPrincipal = new User(jwtConfigProperties.getEmployee().getClientId(), jwtConfigProperties.getEmployee().getClientSecret(),
                true, true, true, true, Collections.emptyList());
        final ResponseEntity<OAuth2AccessToken> response = tokenEndpoint.postAccessToken(
                new UsernamePasswordAuthenticationToken(userPrincipal,
                        jwtConfigProperties.getEmployee().getClientSecret(), Collections.emptyList()), buildLoginParams(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final OAuth2AccessToken oAuth2AccessToken = requireNonNull(response.getBody());
        final com.alseyahat.app.feature.employee.dto.OAuth2AccessToken auth2AccessToken = buildOAuth2AccessToken(oAuth2AccessToken);
//        if (!MOBILE.equalsIgnoreCase(httpRequest.getHeader(USER_AGENT))) {
            ofNullable(response.getBody())
                    .ifPresent(token -> {
//                        httpResponse.addCookie(AppUtils.createTokenCookie(Constants.TOKEN, token.getValue()));
//                        httpResponse.addCookie(AppUtils.createTokenCookie(Constants.REFRESH_TOKEN, token.getRefreshToken().getValue()));
                        auth2AccessToken.setAccessToken(token.getValue());
                        auth2AccessToken.setRefreshToken(token.getRefreshToken().getValue());
                    });
//            auth2AccessToken.setAccessToken(token.getValue());
//            auth2AccessToken.setRefreshToken(token.getRefreshToken().getValue());
//        }
        final Map<String, Object> accessTokenResponse = new ObjectMapper().convertValue(auth2AccessToken, HashMap.class);
        accessTokenResponse.putAll(oAuth2AccessToken.getAdditionalInformation());
        return ResponseEntity.ok(accessTokenResponse);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<OAuth2AccessToken> refreshToken(final String refreshToken) {
        final User userPrincipal = new User(jwtConfigProperties.getEmployee().getClientId(), jwtConfigProperties.getEmployee().getClientSecret(),
                true, true, true, true, Collections.emptyList());
        return tokenEndpoint.postAccessToken(new UsernamePasswordAuthenticationToken(userPrincipal,
                jwtConfigProperties.getEmployee().getClientSecret(), Collections.emptyList()), buildRefreshTokenParams(refreshToken));
    }

    private Map<String, String> buildLoginParams(final EmployeeLoginRequest request) {
        final Map<String, String> params = new HashMap<>();
        params.put(SCOPE, "read write trust");
        params.put(GRANT_TYPE, PASSWORD);
        params.put(USERNAME, request.getEmail() == null ? request.getPhone() : request.getEmail());
        params.put(PASSWORD, request.getPassword());
        params.put(CLIENT_ID, jwtConfigProperties.getEmployee().getClientId());
        params.put(CLIENT_SECRET, jwtConfigProperties.getEmployee().getClientSecret());
        return params;
    }

    private SignUpResponse buildCustomerRegisterResponse(final Employee employee) {
        SignUpResponse response = new SignUpResponse();
        TypeMap<Employee, SignUpResponse> typeMap = modelMapper.typeMap(Employee.class, SignUpResponse.class);
        typeMap.map(employee, response);
        return response;
    }

    private Map<String, String> buildRefreshTokenParams(final String refreshToken) {
        final Map<String, String> params = new HashMap<>();
        params.put(GRANT_TYPE, REFRESH_TOKEN);
        params.put(REFRESH_TOKEN, refreshToken);
        params.put(CLIENT_ID, jwtConfigProperties.getEmployee().getClientId());
        params.put(CLIENT_SECRET, jwtConfigProperties.getEmployee().getClientSecret());
        return params;
    }

//    private void sendBankNotification(final SignUpRequest request, final String password) {
//
//    	final HashMap<String, String> dynamicVariables = new ObjectMapper().convertValue(request, HashMap.class);
//    	dynamicVariables.put(DEFAULT_EMPLOYEE_PASSWORD_KEY, password);
//    	ExecutorService executorService = Executors.newFixedThreadPool(2);
//    	try {
//            ofNullable(request.getPhone()).ifPresent(phone -> {
//                BankMessageTemplateDto smsDto = new BankMessageTemplateDto();
//                smsDto.setEventType(EMPLOYEE_CREATION_EVENT_TYPE);
//                smsDto.setMessageType(NOTIFICATION_TYPE_VALUE_SMS);
//                smsDto.setTo(phone);
//                smsDto.setMessageAppender(dynamicVariables);
//                executorService.submit(() -> bankNotificationService.prepareAndSendMessage(smsDto, "", null, ""));
//            });
//
//            ofNullable(request.getEmail()).ifPresent(email -> {
//                BankMessageTemplateDto emailDto = new BankMessageTemplateDto();
//                emailDto.setEventType(EMPLOYEE_CREATION_EVENT_TYPE);
//                emailDto.setMessageType(NOTIFICATION_TYPE_VALUE_EMAIL);
//                emailDto.setTo(request.getEmail());
//                emailDto.setMessageAppender(dynamicVariables);
//                executorService.submit(() -> bankNotificationService.prepareAndSendMessage(emailDto, "", null, ""));
//                executorService.shutdown();
//            });
//            executorService.shutdown();
//        } catch (Exception ex) {
//            log.error("Error in EmployeeAuthFacadeImpl:sendBankNotification sending bank notification call "
//                    + ex.getMessage());
//            executorService.shutdown();
//        }
//    }


    private com.alseyahat.app.feature.employee.dto.OAuth2AccessToken buildOAuth2AccessToken(final OAuth2AccessToken response) {
        final com.alseyahat.app.feature.employee.dto.OAuth2AccessToken auth2AccessToken = new com.alseyahat.app.feature.employee.dto.OAuth2AccessToken();
        auth2AccessToken.setAccessToken(response.getValue());
        auth2AccessToken.setBearer(response.getTokenType());
        auth2AccessToken.setExpiresIn(response.getExpiresIn());
        auth2AccessToken.setRefreshToken(response.getRefreshToken().getValue());
        auth2AccessToken.setScope(response.getScope());
        auth2AccessToken.setTokenType(response.getTokenType());
        return auth2AccessToken;
    }

}
