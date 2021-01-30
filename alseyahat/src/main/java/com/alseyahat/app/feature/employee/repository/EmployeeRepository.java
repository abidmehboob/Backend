package com.alseyahat.app.feature.employee.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alseyahat.app.feature.employee.repository.entity.Employee;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>, QuerydslPredicateExecutor<Employee> {
}