package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Status;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee get(String id) {
        Optional<Employee> employee = employeeRepository.findById(Integer.parseInt(id));

        return employee.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe este empleado con ID "+ id));
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateStatus(String id, Employee employee) {
        Employee storedEmployee = get(id);

        if (storedEmployee == null) {
            throw new IllegalArgumentException("El empleado no existe");
        } else {
            storedEmployee.setStatus(employee.getStatus());
        }
        return employeeRepository.save(storedEmployee);
    }

    @Override
    public Employee updateDepartment(String id, Employee employee) {
        Employee storedEmployee = get(id);
        //storedEmployee.setDepartment(employee.getDepartment());
        if (employee.getDepartment() == null) {
            throw new IllegalArgumentException("El departamento asignado no es correcto");
        } else {
            storedEmployee.setDepartment(employee.getDepartment());
        }
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
