package com.alseyahat.app.feature.employee.facade.impl;

import com.alseyahat.app.commons.AppUtils;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.employee.dto.ChangePasswordRequest;
import com.alseyahat.app.feature.employee.dto.EmployeeDetailResponse;
import com.alseyahat.app.feature.employee.dto.EmployeeUpdateDetailRequest;
import com.alseyahat.app.feature.employee.dto.ForgotPasswordRequest;
import com.alseyahat.app.feature.employee.dto.UpdateEmployeeResponse;
import com.alseyahat.app.feature.employee.facade.EmployeeFacade;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.alseyahat.app.feature.employee.repository.entity.QEmployee;
import com.alseyahat.app.feature.employee.service.EmployeeService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static com.alseyahat.app.constant.RoleConstant.PERMISSION_DENIED;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeFacadeImpl implements EmployeeFacade {

    ModelMapper modelMapper;

    PasswordEncoder encoder;

    MessageSource messageSource;

    EmployeeService employeeService;


    @Override
    public UpdateEmployeeResponse updateEmployeeDetail(final String employeeId, final EmployeeUpdateDetailRequest request) {
        log.trace("Updating employee [{}]", request);
        final Employee employee = employeeService.findOne(QEmployee.employee.employeeId.eq(employeeId));
//        if(validateEmployeeNotBankSuperAdmin(employee)) {
//        	throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, "Bank Super Admin can be updated");
//        }
        final String username = AppUtils.getUserNameFromAuthentication();
//        final Optional<Employee> loginEmployee = employeeService
//                .find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
//        if (loginEmployee.isPresent()) {
                  
                  final Employee updatedEmployee = employeeService.saveWithRollback(employee);
                  log.trace("Updating employee [{}]", request);
                  return buildUpdateEmployeeResponse(updatedEmployee);
           
//        }else
//        	 throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
      
    }

    @Override
    public void activateEmployee(final String employeeId) {
        log.trace("Activating employee with employeeId [{}]", employeeId);
        final Employee employee = employeeService.findOne(QEmployee.employee.employeeId.eq(employeeId));
//        final Optional<Employee> loginEmployee = employeeService.find_One(QEmployee.employee.email.eq(AppUtils.getUserNameFromAuthentication())
//                .or(QEmployee.employee.phone.eq(AppUtils.getUserNameFromAuthentication())));
//        if (loginEmployee.isPresent()) {
        	 employee.setEnabled(Boolean.TRUE);
             employeeService.saveWithRollback(employee);
             log.trace("Employee deactivated with employeeId [{}]", employeeId);
       
//        }else
//        	 throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
    }
    
//    private boolean validateEmployeeNotBankSuperAdmin(final Employee employee) {
//    	List<EmployeeBranchRoles> loginEmployeeRoleLst = employeeBrachRolesService.findAll(QEmployeeBranchRoles.employeeBranchRoles.employee.employeeId.eq(employee.getEmployeeId()), Pageable.unpaged()).getContent();
//    	if (loginEmployeeRoleLst.stream().anyMatch(role -> BANK_SUPER_ADMIN.equals(role.getRole().getName())&& TYPE_ZERO.equals(role.getRole().getType()))) {
//    		return true;
//    	}
//    	return false;
//    }

    @Override
    public void deActivateEmployee(final String employeeId) {
        log.trace("Deactivating employee with employeeId [{}]", employeeId);
        final Employee employee = employeeService.findOne(QEmployee.employee.employeeId.eq(employeeId));
//        if(validateEmployeeNotBankSuperAdmin(employee)) {
//        	throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, "Bank Super Admin can be updated");
//        }
        
//        final Optional<Employee> loginEmployee = employeeService.find_One(QEmployee.employee.email.eq(AppUtils.getUserNameFromAuthentication())
//                .or(QEmployee.employee.phone.eq(AppUtils.getUserNameFromAuthentication())));
//        if (loginEmployee.isPresent()) {
        	 employee.setEnabled(Boolean.FALSE);
             employeeService.saveWithRollback(employee);
             log.trace("Employee deactivated with employeeId [{}]", employeeId);
    
//        }else
//        	 throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
       
    }

    @Override
    public void forgotPasswordEmail(final ForgotPasswordRequest request) {
        ofNullable(employeeService.findOne(QEmployee.employee.email.eq(request.getEmail())))
                .ifPresent(employee -> {
                    Map<String, Object> content = new HashMap<>();
                    content.put("name", employee.getName());
//                    EmailDto emailDto = new EmailDto();
//                    emailDto.setContent(content);
//                    emailDto.setFrom(messageSource.getMessage("employee.email.forgot_password.from", null, LocaleContextHolder.getLocale()));
//                    emailDto.setTo(new String[]{request.getEmail()});
//                    emailDto.setSubject(messageSource.getMessage("employee.email.forgot_password.subject", null, LocaleContextHolder.getLocale()));
//                    notificationService.prepareAndSendMessage(emailDto, "email", Locale.forLanguageTag("en"), "/employee-forgot-password-email-tmpl.ftl");
                    log.debug("Email sent to email [{}]", request.getEmail());
                });
    }

    @Override
    public void changePassword(final ChangePasswordRequest request) {
        log.trace("Updating password of employee with employee email [{}]", request.getEmail());
        final String username = AppUtils.getUserNameFromAuthentication();
        final Optional<Employee> loginemployee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
        
        final Employee employee = employeeService.findOne(QEmployee.employee.email.eq(request.getEmail()).and(QEmployee.employee.isEnabled.isTrue()));
        
        if(!loginemployee.get().getEmployeeId().equals(employee.getEmployeeId()))
        	throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);
        
        if (BooleanUtils.isFalse(encoder.matches(request.getOldPassword(), employee.getPassword()))) {
            log.debug("Old password does not match for email [{}]", request.getEmail());
            throw new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "Invalid old password");
        }
        employee.setPassword(encoder.encode(request.getNewPassword()));
        employeeService.saveWithRollback(employee);
        log.trace("Password updated of employee with id [{}]", employee.getEmail());
    }

    @Override
    public EmployeeDetailResponse findEmployeeById(final String employeeId) {
        log.trace("Finding employee wit id [{}]", employeeId);
        final Employee employeeWithId = employeeService.findOne(QEmployee.employee.employeeId.eq(employeeId).and(QEmployee.employee.isEnabled.isTrue()));
        final String username = AppUtils.getUserNameFromAuthentication();
        final Optional<Employee> employee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
        if (employee.isPresent()) {
                      return buildEmployeeDetailResponse(employeeWithId);
        } else
            throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);


    }

    @Override
    public EmployeeDetailResponse findEmployeeDetail() {
        String username = AppUtils.getUserNameFromAuthentication();
        final Employee employee = employeeService.findOne((QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username))).and(QEmployee.employee.isEnabled.isTrue()));
        return buildEmployeeDetailResponse(employee);
    }

    @Override
    public Page<EmployeeDetailResponse> findAllEmployees(final Predicate predicate, final Pageable pageable) {
        log.trace("Finding employees predicate [{}]", predicate);
//        final String username = AppUtils.getUserNameFromAuthentication();
//        final Optional<Employee> employee = employeeService.find_One(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
//        if (employee.isPresent()) {
            return employeeService.findAll(ExpressionUtils.allOf(predicate), pageable).map(this::buildEmployeeDetailResponse);
//        } else
//            throw new ServiceException(ErrorCodeEnum.INVALID_REQUEST, PERMISSION_DENIED);

    }

    private UpdateEmployeeResponse buildUpdateEmployeeResponse(final Employee employee) {
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        TypeMap<Employee, UpdateEmployeeResponse> employeeTypeMap = modelMapper.typeMap(Employee.class, UpdateEmployeeResponse.class);
        employeeTypeMap.map(employee, response);
        return response;
    }

    private EmployeeDetailResponse buildEmployeeDetailResponse(final Employee employee) {
        EmployeeDetailResponse response = new EmployeeDetailResponse();
        response.setEmail(employee.getEmail());
        response.setName(employee.getName());
        response.setEmployeeId(employee.getEmployeeId());
        response.setIsEnabled(employee.isEnabled());
        response.setPhone(employee.getPhone());
//        Page<EmployeeBranchRoles> employeeRolelst = employeeBrachRolesService.findAll(QEmployeeBranchRoles.employeeBranchRoles.employee.employeeId.eq(employee.getEmployeeId()),Pageable.unpaged());
//
//        List<EmployeeBranchRolesResponse> employeeRoles = new ArrayList<EmployeeBranchRolesResponse>();
//        employeeRolelst.stream().forEach(role -> {
//        	EmployeeBranchRolesResponse roleResponse = new EmployeeBranchRolesResponse();
//        	if(StringUtils.isNotEmpty(role.getBranchId())) {
//        		final Optional<Branch> branch = branchService.find_One(QBranch.branch.branchId.eq(role.getBranchId()).and(QBranch.branch.isEnabled.isTrue()));
//    	    	if(branch.isPresent()) {
//    	    		roleResponse.setBranchId(branch.get().getBranchId());
//        	    	roleResponse.setBranchName(branch.get().getName());
//        	    	roleResponse.setStoreId(branch.get().getStore().getStoreId());
//        	    	roleResponse.setStoreName(branch.get().getStore().getName());
//    	    	}
//        		
//        	}
//	    	roleResponse.setEmployeeName(employee.getName());
//	    	roleResponse.setRoleId(role.getRole().getRoleId()); 
//	    	roleResponse.setRoleName(role.getRole().getName());
//	    	employeeRoles.add(roleResponse);
//        });
//        
//        response.setRoles(employeeRoles);
        return response;
    }

}
