package com.gevernova.employeerollapp.service;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;
import jakarta.mail.MessagingException;


import java.util.List;

public interface IEmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(int id);
    EmployeeDTO create(EmployeeDTO employeeDTO) throws MessagingException;

    EmployeeDTO updateEmployee(int id, EmployeeDTO updatedEmp);
    Void deleteEmployee(int id);
}
