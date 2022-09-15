package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Status;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

     @Autowired
    private EmployeeService employeeService;

     @GetMapping("/employees")
    public List<Employee> list() {
         return employeeService.list();
     }

    @GetMapping("/employees/{id}")
    public Employee get(@PathVariable String id) {
        return employeeService.get(id);
    }

    @PostMapping("/employees/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/employees/status/{status}")
    public List<Employee> findByStatus(@PathVariable Status status){
        return employeeService.findByStatus(status);
    }

    @GetMapping("/employees/department/{department}")
    public List<Employee> findByDepartment(@PathVariable Department department){
        return employeeService.findByDepartment(department);
    }

    @PatchMapping ("/employees/{id}/change/status")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee changeStatus(@RequestBody Employee employee, @PathVariable String id) {
         return employeeService.updateStatus(id, employee);
    }

    @PatchMapping ("/employees/{id}/change/department")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee changeDepartment(@RequestBody Employee employee, @PathVariable String id) {
        return employeeService.updateDepartment(id, employee);
    }
}
