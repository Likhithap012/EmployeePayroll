package com.gevernova.employeerollapp.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String name;
    private long salary;
    private List<String> departments;
}

