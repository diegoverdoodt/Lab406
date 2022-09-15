package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Status;

import java.util.List;

public interface EmployeeService {

    Employee get(String id);

    Employee save(Employee employee);

    Employee updateStatus(String id, Employee employee);

    Employee updateDepartment(String id, Employee employee);

    void delete(Employee employee);

    List<Employee> list();

    List<Employee> findByStatus(Status status);

    List<Employee> findByDepartment(Department department);
}
