package com.alseyahat.app.feature.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.employee.repository.EmployeeRoleRepository;
import com.alseyahat.app.feature.employee.repository.entity.EmployeeRole;
import com.alseyahat.app.feature.employee.service.EmployeeRoleService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	EmployeeRoleRepository employeeRoleRepository;

    @Override
    public EmployeeRole save(final EmployeeRole employeeRole) {
        return employeeRoleRepository.save(employeeRole);
    }

    @Override
    public List<EmployeeRole> saveAll(List<EmployeeRole> employeesRole) {
        return IterableUtils.toList(employeeRoleRepository.saveAll(employeesRole));
    }

    @Override
    public EmployeeRole findOne(final Predicate predicate) {
        return employeeRoleRepository.findOne(predicate)
                .orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.employee_entity_not_found"));
    }
    
    @Override
    public Optional<EmployeeRole> find_One(final Predicate predicate) {
        return employeeRoleRepository.findOne(predicate);
    }

    @Override
    public List<EmployeeRole> findAll(Predicate predicate) {
        return IterableUtils.toList(employeeRoleRepository.findAll(predicate));
    }

    @Override
    public Page<EmployeeRole> findAll(final Predicate predicate, final Pageable pageable) {
        return employeeRoleRepository.findAll(predicate, pageable);
    }

	@Override
    @Transactional(rollbackFor = Exception.class)
	public EmployeeRole saveWithRollback(EmployeeRole employee) {
		return employeeRoleRepository.save(employee);
	}

}

