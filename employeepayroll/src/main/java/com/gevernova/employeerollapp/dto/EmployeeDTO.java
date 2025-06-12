package com.gevernova.employeerollapp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 1000, message = "Salary must be at least 1000")
    private long salary;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Departments cannot be empty")
    private List<@NotBlank(message = "Department name cannot be blank") String> departments;
}

