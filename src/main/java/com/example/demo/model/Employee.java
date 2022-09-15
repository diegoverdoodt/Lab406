package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    //@Column(name = "employee_id")
    private Integer employeeId;

    //@Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    //@Column(name = "name")
    private String name;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "admittedBy")
    private List<Patient> patientList;
    public int getEmployeeId() {
        return employeeId;
    }


    public Employee(){}

    public Employee(Integer employeeId, Department department, String name, Status status) {
        this.employeeId = employeeId;
        this.department = department;
        this.name = name;
        this.status = status;
    }

    public void setEmployeeId(Integer employeId) {
        this.employeeId = employeId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
