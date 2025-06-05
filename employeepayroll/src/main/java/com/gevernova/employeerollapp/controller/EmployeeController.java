package com.gevernova.employeerollapp.controller;

import com.gevernova.employeerollapp.entity.Employee;
import com.gevernova.employeerollapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/employeepayrollservice")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService service;
//
//    @GetMapping("/")
//    public List<Employee> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("/get/{id}")
//    public Employee getById(@PathVariable int id) {
//        return service.getById(id);
//    }
//
//    @PostMapping("/create")
//    public Employee create(@RequestBody Employee emp) {
//        return service.create(emp);
//    }
//
//    @PutMapping("/update")
//    public Employee update(@RequestBody Employee emp) {
//        return service.update(emp);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable int id) {
//        service.delete(id);
//        return "Deleted employee with id " + id;
//    }
//}

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee emp) {
        return service.createEmployee(emp);
    }

    @PutMapping("/update/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee emp) {
        return service.updateEmployee(id, emp);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return service.deleteEmployee(id) ? "Deleted" : "Not Found";
    }
}
