package com.gevernova.employeerollapp.service;

import com.gevernova.employeerollapp.entity.Employee;
import com.gevernova.employeerollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepository repo;
//
//    public List<Employee> getAll() {
//        return repo.findAll();
//    }
//
//    public Employee getById(int id) {
//        return repo.findById(id).orElse(null);
//    }
//
//    public Employee create(Employee emp) {
//        return repo.save(emp);
//    }
//
//    public Employee update(Employee emp) {
//        return repo.save(emp);
//    }
//
//    public void delete(int id) {
//        repo.deleteById(id);
//    }
//}
@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private int currentId = 1;

    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Employee getEmployeeById(int id) {
        return employeeList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Employee createEmployee(Employee emp) {
        emp.setId(currentId++);
        employeeList.add(emp);
        return emp;
    }

    public Employee updateEmployee(int id, Employee updatedEmp) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getId() == id) {
                updatedEmp.setId(id);
                employeeList.set(i, updatedEmp);
                return updatedEmp;
            }
        }
        return null;
    }

    public boolean deleteEmployee(int id) {
        return employeeList.removeIf(emp -> emp.getId() == id);
    }
}

