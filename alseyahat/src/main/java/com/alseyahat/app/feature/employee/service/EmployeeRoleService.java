package com.alseyahat.app.feature.employee.service;

import com.alseyahat.app.feature.employee.repository.entity.EmployeeRole;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeRoleService {

	EmployeeRole save(final EmployeeRole employee);

	EmployeeRole saveWithRollback(final EmployeeRole employee);

    List<EmployeeRole> saveAll(final List<EmployeeRole> employeeRole);

    EmployeeRole findOne(final Predicate predicate);

    List<EmployeeRole> findAll(final Predicate predicate);

    Page<EmployeeRole> findAll(final Predicate predicate, final Pageable pageable);
    
    Optional<EmployeeRole> find_One(final Predicate predicate);
    
}