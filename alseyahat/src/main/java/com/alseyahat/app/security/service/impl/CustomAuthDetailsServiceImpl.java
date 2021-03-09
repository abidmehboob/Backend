package com.alseyahat.app.security.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alseyahat.app.feature.customer.repository.CustomerRepository;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.customer.repository.entity.QCustomer;
import com.alseyahat.app.feature.employee.repository.EmployeeRepository;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.alseyahat.app.feature.employee.repository.entity.QEmployee;
import com.alseyahat.app.security.service.CustomAuthDetailsService;

import java.util.Optional;
import java.util.UUID;


@Primary
@RequiredArgsConstructor
@Service("employeeAuthDetailsService")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomAuthDetailsServiceImpl implements CustomAuthDetailsService {

    CustomerRepository customerRepository;

    EmployeeRepository employeeRepository;
    
//    EmployeeBrachRolesService employeeBrachRolesService;
    
//    BranchService branchService;
    
    @Override
    public UserDetails loadUserByUsername(final String username) {
		
    	final Optional<Customer> customer = customerRepository.findOne(QCustomer.customer.personalKey.eq(username));
        if (customer.isPresent()) {
        	final Customer updateSecurityKey = customer.get();
            updateSecurityKey.setSecurityKey(UUID.randomUUID().toString().replace("-", ""));
            updateSecurityKey.setAesKey(UUID.randomUUID().toString().replace("-", ""));
            customerRepository.save(updateSecurityKey);
            return updateSecurityKey;
        }
        final Optional<Employee> employee = employeeRepository.findOne(QEmployee.employee.email.eq(username).or(QEmployee.employee.phone.eq(username)));
		if (employee.isPresent()) {
			Employee updateSecurityKey = employee.get();
			updateSecurityKey.setSecurityKey(UUID.randomUUID().toString().replace("-", ""));
			updateSecurityKey.setAesKey(UUID.randomUUID().toString().replace("-", ""));
            employeeRepository.save(updateSecurityKey); 
            return updateSecurityKey;
        }
        throw new UsernameNotFoundException("error.user_email_or_phone_not_found");
    }
}
