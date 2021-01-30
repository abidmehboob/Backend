package com.alseyahat.app.feature.employee.service;

import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee save(final Employee employee);

    Employee saveWithRollback(final Employee employee);

    List<Employee> saveAll(final List<Employee> employees);

    Employee findOne(final Predicate predicate);

    List<Employee> findAll(final Predicate predicate);

    Page<Employee> findAll(final Predicate predicate, final Pageable pageable);
    
    Optional<Employee> find_One(final Predicate predicate);
    
}
