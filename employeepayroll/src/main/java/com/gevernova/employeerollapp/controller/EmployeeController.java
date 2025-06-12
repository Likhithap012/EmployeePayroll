package com.gevernova.employeerollapp.controller;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.entity.Employee;
import com.gevernova.employeerollapp.mapper.EmployeeMapper;
import com.gevernova.employeerollapp.service.EmployeeService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        List<EmployeeDTO> list = service.getAllEmployees()
                .stream().map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable int id) {
        Employee emp = service.getEmployeeById(id);
        return ResponseEntity.ok(mapper.toDTO(emp));
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO dto) throws MessagingException {
        EmployeeDTO saved = service.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable int id, @Valid @RequestBody EmployeeDTO dto) {
        Employee updated = service.updateEmployee(id, mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        boolean deleted = service.deleteEmployee(id);
        return ResponseEntity.ok(deleted ? "Deleted" : "Not Found");
    }
}
