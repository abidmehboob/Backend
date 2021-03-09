package com.alseyahat.app.feature.employee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeDetailResponse {

    String employeeId;

    String name;

    String email;

    String phone;

    String branchId;

    Boolean isEnabled;

    Date dateCreated;

    Date lastUpdated;

//    List<EmployeeBranchRolesResponse> roles = new ArrayList<EmployeeBranchRolesResponse>();

}

