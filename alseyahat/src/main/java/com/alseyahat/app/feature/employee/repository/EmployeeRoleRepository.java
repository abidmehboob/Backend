package com.alseyahat.app.feature.employee.repository;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.alseyahat.app.feature.employee.repository.entity.EmployeeRole;


public interface EmployeeRoleRepository extends PagingAndSortingRepository<EmployeeRole, String>, QuerydslPredicateExecutor<EmployeeRole> {
}