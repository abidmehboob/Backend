package com.alseyahat.app.feature.employee.service.impl;

import com.alseyahat.app.exception.ServiceException;
import com.alseyahat.app.exception.constant.ErrorCodeEnum;
import com.alseyahat.app.feature.employee.repository.EmployeeRepository;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.alseyahat.app.feature.employee.service.EmployeeService;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeServiceImpl implements EmployeeService {


    EmployeeRepository employeeRepository;

    @Override
    public Employee save(final Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        return IterableUtils.toList(employeeRepository.saveAll(employees));
    }

    @Override
    public Employee findOne(final Predicate predicate) {
        return employeeRepository.findOne(predicate)
                .orElseThrow(() -> new ServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "error.employee_entity_not_found"));
    }
    
    @Override
    public Optional<Employee> find_One(final Predicate predicate) {
        return employeeRepository.findOne(predicate);
    }

    @Override
    public List<Employee> findAll(Predicate predicate) {
        return IterableUtils.toList(employeeRepository.findAll(predicate));
    }

    @Override
    public Page<Employee> findAll(final Predicate predicate, final Pageable pageable) {
        return employeeRepository.findAll(predicate, pageable);
    }

	@Override
    @Transactional(rollbackFor = Exception.class)
	public Employee saveWithRollback(Employee employee) {
		return employeeRepository.save(employee);
	}


}
