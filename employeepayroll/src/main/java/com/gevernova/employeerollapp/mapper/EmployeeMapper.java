package com.gevernova.employeerollapp.mapper;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDTO dto) {
        return Employee.builder()
                .name(dto.getName())
                .salary(dto.getSalary())
                .email(dto.getEmail())
                .departments(dto.getDepartments())
                .build();
    }

    public EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getName(),
                employee.getSalary(),
                employee.getEmail(),
                employee.getDepartments()
        );
    }
}
