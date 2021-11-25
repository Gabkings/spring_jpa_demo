package com.gab.jpa_demo.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestVO {
    @NotNull
    @JsonProperty("empName")
    private String empName;
    @NotNull
    @JsonProperty("email")
    private String email;

    @JsonProperty("departmentName")
    private String departmentName;
}
