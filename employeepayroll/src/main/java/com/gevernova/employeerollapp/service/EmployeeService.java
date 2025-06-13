package com.gevernova.employeerollapp.service;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;
import com.gevernova.employeerollapp.exception.EmployeeNotFoundException;
import com.gevernova.employeerollapp.mapper.EmployeeMapper;
import com.gevernova.employeerollapp.repository.EmployeeRepository;
import com.gevernova.employeerollapp.util.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;
    private final EmailService emailService;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees from database");
        return employeeRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        log.info("Fetching employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with ID: {}", id);
                    return new EmployeeNotFoundException("Employee not found with ID: " + id);
                });
        return mapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) throws MessagingException {
        log.info("Creating new employee: {}", employeeDTO.getEmail());
        Employee employeeData = employeeRepository.save(mapper.toEntity(employeeDTO));

        log.info("Sending confirmation email to {}", employeeDTO.getEmail());
        emailService.sendEmail(
                employeeDTO.getEmail(),
                "Register Successful",
                "Hi " + employeeDTO.getName() + ", you have registered successfully.\nDetails:\n" + employeeData
        );

        log.info("Employee created and email sent: {}", employeeData.getId());
        return mapper.toDTO(employeeData);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO updatedEmp) {
        log.info("Updating employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Cannot update. Employee not found with ID: {}", id);
            return new EmployeeNotFoundException("Employee not found with ID: " + id);
        });

        employee.setEmail(updatedEmp.getEmail());
        employee.setDepartments(updatedEmp.getDepartments());
        employee.setName(updatedEmp.getName());
        employee.setSalary(updatedEmp.getSalary());

        employeeRepository.save(employee);
        log.info("Employee updated successfully with ID: {}", id);
        return mapper.toDTO(employee);
    }

    @Override
    public Void deleteEmployee(int id) {
        log.info("Deleting employee with ID: {}", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
            log.error("Cannot delete. Employee not found with ID: {}", id);
            return new EmployeeNotFoundException("Cannot delete. Employee not found with ID: " + id);
        });
        employeeRepository.deleteById(id);
        log.info("Employee deleted successfully with ID: {}", id);
        return null;
    }
}
