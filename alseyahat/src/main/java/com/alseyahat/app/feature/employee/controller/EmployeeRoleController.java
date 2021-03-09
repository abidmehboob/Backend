package com.alseyahat.app.feature.employee.controller;

import com.alseyahat.app.feature.employee.dto.RoleRequest;
import com.alseyahat.app.feature.employee.dto.RoleResponse;
import com.alseyahat.app.feature.employee.facade.EmployeeRoleFacade;
import com.alseyahat.app.feature.employee.repository.entity.EmployeeRole;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Api(tags = "Employee Role")
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRoleController {

    EmployeeRoleFacade employeeRoleFacade;

    @GetMapping
    @ApiOperation(value = "Get all EmployeeRoles", nickname = "getAllEmployeeRoles", notes = "Get all employee roles")
    public ResponseEntity<Page<RoleResponse>> getAllEmployeeRoles(@RequestParam Map<String, String> parameters, @QuerydslPredicate(root = EmployeeRole.class) Predicate predicate, final Pageable pageable) {
         return ResponseEntity.ok(employeeRoleFacade.findAllEmployeeRoles(predicate, pageable));
    }

    @ApiOperation(value = "Create employee role", nickname = "createEmployeeRole", notes = "Create employee role")
    @PostMapping
    public ResponseEntity<RoleResponse> createEmployeeRole(@Valid @RequestBody final RoleRequest request) {
        return new ResponseEntity<>(employeeRoleFacade.createEmployeeRole(request), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update employee role", nickname = "updateEmployeeRole", notes = "Update employee role")
    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponse> updateEmployeeRole(@PathVariable final Long roleId,
                                                           @Valid @RequestBody final RoleRequest request) {
        return new ResponseEntity<>(employeeRoleFacade.updateEmployeeRole(roleId, request), HttpStatus.OK);
    }

}
