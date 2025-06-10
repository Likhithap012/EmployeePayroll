package com.gevernova.employeerollapp.service;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee createEmployee(Employee emp);
    Employee createEmployee(EmployeeDTO dto);
    Employee updateEmployee(int id, Employee updatedEmp);
    boolean deleteEmployee(int id);
}
