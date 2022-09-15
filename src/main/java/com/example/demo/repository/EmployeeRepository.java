package com.example.demo.repository;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findAllByStatus(Status status);

    List<Employee> findAllByDepartment(Department department);
}
