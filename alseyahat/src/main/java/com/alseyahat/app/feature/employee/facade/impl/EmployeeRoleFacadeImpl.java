package com.alseyahat.app.feature.employee.facade.impl;

import com.alseyahat.app.feature.employee.dto.RoleRequest;
import com.alseyahat.app.feature.employee.dto.RoleResponse;
import com.alseyahat.app.feature.employee.facade.EmployeeRoleFacade;
import com.alseyahat.app.feature.employee.repository.entity.EmployeeRole;
import com.alseyahat.app.feature.employee.repository.entity.QEmployeeRole;
import com.alseyahat.app.feature.employee.service.EmployeeRoleService;
import com.alseyahat.app.feature.hotel.dto.HotelDetailResponse;
import com.alseyahat.app.feature.hotel.repository.entity.Hotel;
import com.alseyahat.app.feature.hotel.repository.entity.QHotelFacility;
import com.alseyahat.app.feature.review.repository.entity.QReview;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeRoleFacadeImpl implements EmployeeRoleFacade {

    EmployeeRoleService employeeRoleService;
    ModelMapper modelMapper;
    @Override
	public Page<RoleResponse> findAllEmployeeRoles(Predicate predicate, Pageable pageable) {
		return employeeRoleService.findAll(predicate, pageable).map(this::buildEmployeeRoleDetailResponse);
	}
	
    
    
    @Override
    public RoleResponse findEmployeeRole(final Predicate predicate) {
        final EmployeeRole employeeRole = employeeRoleService.findOne(predicate);
        final RoleResponse response = new RoleResponse();
        response.setId(employeeRole.getRoleId());
        response.setName(response.getName());
        response.setType(response.getType());
        return response;
    }

    @Override
    public RoleResponse createEmployeeRole(final RoleRequest request) {
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setName(request.getName());
        employeeRole.setType(request.getType());
        final EmployeeRole savedEmployeeRole = employeeRoleService.save(employeeRole);
        RoleResponse response = new RoleResponse();
        response.setName(savedEmployeeRole.getName());
        response.setId(savedEmployeeRole.getRoleId());
        return response;
    }

    @Override
    public RoleResponse updateEmployeeRole(final Long roleId, final RoleRequest request) {
        final EmployeeRole employeeRole = employeeRoleService.findOne(QEmployeeRole.employeeRole.roleId.eq(roleId));
        employeeRole.setName(request.getName());
        employeeRole.setType(request.getType());
        final EmployeeRole updatedEmployeeRole = employeeRoleService.save(employeeRole);
        RoleResponse response = new RoleResponse();
        response.setName(updatedEmployeeRole.getName());
        response.setType(updatedEmployeeRole.getType());
        response.setId(updatedEmployeeRole.getRoleId());
        return response;
    }
    
	private RoleResponse buildEmployeeRoleDetailResponse(final EmployeeRole rmployeeRole) {
		RoleResponse response = new RoleResponse();
		modelMapper.getConfiguration().setAmbiguityIgnored(Boolean.TRUE);
		TypeMap<EmployeeRole, RoleResponse> typeMap = modelMapper.typeMap(EmployeeRole.class, RoleResponse.class);
		typeMap.map(rmployeeRole, response);
	
		return response;
	}
}
