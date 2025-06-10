package com.gevernova.employeerollapp.service;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;
import com.gevernova.employeerollapp.exception.EmployeeNotFoundException;
import com.gevernova.employeerollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    @Override
    public Employee createEmployee(Employee emp) {
        return repository.save(emp);
    }

    @Override
    public Employee createEmployee(EmployeeDTO dto) {
        Employee employee = Employee.builder()
                .name(dto.getName())
                .salary(dto.getSalary())
                .departments(dto.getDepartments())
                .build();
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee updatedEmp) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException("Cannot update. Employee not found with ID: " + id);
        }
        updatedEmp.setId(id);
        return repository.save(updatedEmp);
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (!repository.existsById(id)) {
            throw new EmployeeNotFoundException("Cannot delete. Employee not found with ID: " + id);
        }
        repository.deleteById(id);
        return true;
    }
}
