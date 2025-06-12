package com.gevernova.employeerollapp.service;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;
import com.gevernova.employeerollapp.exception.EmployeeNotFoundException;
import com.gevernova.employeerollapp.mapper.EmployeeMapper;
import com.gevernova.employeerollapp.repository.EmployeeRepository;
import com.gevernova.employeerollapp.util.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }

    @Override
    public Employee createEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) throws MessagingException {
        Employee employeeData = employeeRepository.save(mapper.toEntity(employeeDTO));

        emailService.sendEmail(
                employeeDTO.getEmail(),
                "Register Successful",
                "Hi " + employeeDTO.getName() + ", you have registered successfully.\nDetails:\n" + employeeData
        );

        return mapper.toDTO(employeeData);
    }

    @Override
    public Employee updateEmployee(int id, Employee updatedEmp) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Cannot update. Employee not found with ID: " + id);
        }
        updatedEmp.setId(id);
        return employeeRepository.save(updatedEmp);
    }

    @Override
    public boolean deleteEmployee(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Cannot delete. Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
