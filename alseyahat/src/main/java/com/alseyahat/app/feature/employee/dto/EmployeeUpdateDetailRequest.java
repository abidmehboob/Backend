package com.alseyahat.app.feature.employee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeUpdateDetailRequest {


    @NotEmpty
    String name;

    @NotEmpty
    String email;

    String phone;

    List<@NotEmpty String> branches = new ArrayList<>();

    List<@NotNull Long> roles = new ArrayList<>();


}
