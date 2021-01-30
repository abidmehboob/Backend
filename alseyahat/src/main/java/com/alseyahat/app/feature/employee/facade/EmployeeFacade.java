package com.alseyahat.app.feature.employee.facade;


import com.alseyahat.app.feature.employee.dto.ChangePasswordRequest;
import com.alseyahat.app.feature.employee.dto.EmployeeDetailResponse;
import com.alseyahat.app.feature.employee.dto.EmployeeUpdateDetailRequest;
import com.alseyahat.app.feature.employee.dto.ForgotPasswordRequest;
import com.alseyahat.app.feature.employee.dto.UpdateEmployeeResponse;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeFacade {


    void activateEmployee(final String employeeId);

    void deActivateEmployee(final String employeeId);

    void forgotPasswordEmail(final ForgotPasswordRequest request);

    void changePassword(final ChangePasswordRequest request);

    EmployeeDetailResponse findEmployeeById(final String employeeId);

    Page<EmployeeDetailResponse> findAllEmployees(final Predicate predicate, final Pageable pageable);

    UpdateEmployeeResponse updateEmployeeDetail(final String employeeId, final EmployeeUpdateDetailRequest request);

    EmployeeDetailResponse findEmployeeDetail();
}
