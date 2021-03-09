package com.alseyahat.app.feature.employee.controller;


import com.alseyahat.app.feature.employee.dto.ChangePasswordRequest;
import com.alseyahat.app.feature.employee.dto.EmployeeDetailResponse;
import com.alseyahat.app.feature.employee.dto.EmployeeUpdateDetailRequest;
import com.alseyahat.app.feature.employee.dto.UpdateEmployeeResponse;
import com.alseyahat.app.feature.employee.facade.EmployeeFacade;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Api(tags = "Employee")
@RequestMapping("/employees")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeController {


    EmployeeFacade employeeFacade;


    @ApiOperation(value = "Update employee detail", nickname = "updateEmployee", notes = "Employee login")
    @PutMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateEmployeeResponse> updateEmployee(@PathVariable("employeeId") final String employeeId, @Valid @RequestBody final EmployeeUpdateDetailRequest request) {
        return new ResponseEntity<>(employeeFacade.updateEmployeeDetail(employeeId, request), HttpStatus.OK);
    }

    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get employee detail by id", nickname = "getEmployeeDetailById", notes = "Get employee detail by id")
    public ResponseEntity<EmployeeDetailResponse> getEmployeeDetailById(@PathVariable("employeeId") final String employeeId) {
        return new ResponseEntity<>(employeeFacade.findEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping(value = "/own-detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get employee detail", nickname = "getEmployeeDetails", notes = "Get employee details")
    public ResponseEntity<EmployeeDetailResponse> getEmployeeDetail() {
        return new ResponseEntity<>(employeeFacade.findEmployeeDetail(), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all employees", nickname = "getAllEmployees", notes = "Get all employees")
    public ResponseEntity<Page<EmployeeDetailResponse>> getAllEmployees(@QuerydslPredicate(root = Employee.class) final Predicate predicate, final Pageable pageable) {
        return new ResponseEntity<>(employeeFacade.findAllEmployees(predicate, pageable), HttpStatus.OK);
    }


    @PostMapping(value = "/deActive/{employeeId}")
    @ApiOperation(value = "De activate employee", nickname = "deactivateEmployee", notes = "De activate employee")
    public ResponseEntity<Void> deactivateEmployee(@PathVariable("employeeId") final String employeeId) {
        employeeFacade.deActivateEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/activate/{employeeId}")
    @ApiOperation(value = "Activate Employee", nickname = "activateEmployee", notes = "De activate employee")
    public ResponseEntity<Void> activateEmployee(@PathVariable("employeeId") final String employeeId) {
        employeeFacade.activateEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/change-password", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change employee password", nickname = "changePassword", notes = "Change employee password")
    public ResponseEntity<Void> changePassword(@RequestBody final ChangePasswordRequest request) {
        employeeFacade.changePassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping(value = "/forgot-password-email", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "Send email to employee for forgot password", nickname = "forgotPasswordEmail", notes = "Send email to employee for forgot password")
//    public ResponseEntity<Void> forgotPasswordEmail(@Valid @RequestBody final ForgotPasswordRequest request) {
//        employeeFacade.forgotPasswordEmail(request);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
