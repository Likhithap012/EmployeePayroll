package com.gevernova.employeerollapp.controller;

import com.gevernova.employeerollapp.dto.EmployeeDTO;
import com.gevernova.employeerollapp.mapper.EmployeeMapper;
import com.gevernova.employeerollapp.service.EmployeeService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    private final EmployeeMapper mapper;

    //http://localhost:8080/employeepayrollservice/
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        List<EmployeeDTO> list = service.getAllEmployees();
        return ResponseEntity.ok(list);
    }
    //http://localhost:8080/employeepayrollservic/get/1
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable int id) {
        EmployeeDTO emp = service.getEmployeeById(id);
        return ResponseEntity.ok(emp);
    }
    //http://localhost:8080/employeepayrollservice/create
    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO dto) throws MessagingException {
        EmployeeDTO saved = service.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

//    http://localhost:8080/employeepayrollservice/update/1
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDTO> update(@PathVariable int id, @Valid @RequestBody EmployeeDTO dto) {
        EmployeeDTO updated = service.updateEmployee(id, dto);
        return ResponseEntity.ok(updated);
    }

//    http://localhost:8080/employeepayrollservice/delete/1
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok("Employee with ID " + id + " has been deleted successfully.");
    }

}
