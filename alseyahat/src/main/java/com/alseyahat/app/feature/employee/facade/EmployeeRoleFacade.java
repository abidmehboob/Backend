package com.alseyahat.app.feature.employee.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alseyahat.app.feature.employee.dto.RoleRequest;
import com.alseyahat.app.feature.employee.dto.RoleResponse;
import com.querydsl.core.types.Predicate;
public interface EmployeeRoleFacade {

    RoleResponse findEmployeeRole(final Predicate predicate);

    RoleResponse createEmployeeRole(final RoleRequest request);

    RoleResponse updateEmployeeRole(final Long roleId, final RoleRequest request);
    
	Page<RoleResponse> findAllEmployeeRoles(final Predicate predicate, final Pageable pageable);

}
