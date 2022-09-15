package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Status;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee get(String id) {
        Integer intId = Integer.parseInt(id);
        return employeeRepository.findById(intId).get();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateStatus(String id, Employee employee) {
        Employee storedEmployee = get(id);
        storedEmployee.setStatus(employee.getStatus());

        return employeeRepository.save(storedEmployee);
    }

    @Override
    public Employee updateDepartment(String id, Employee employee) {
        Employee storedEmployee = get(id);
        storedEmployee.setDepartment(employee.getDepartment());

        return employeeRepository.save(storedEmployee);
    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByStatus(Status status){

        return employeeRepository.findAllByStatus(status);
    }

    @Override
    public List<Employee> findByDepartment(Department department) {
        return employeeRepository.findAllByDepartment(department);
    }
}
