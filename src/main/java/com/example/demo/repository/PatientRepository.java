package com.example.demo.repository;

import com.example.demo.model.Department;
import com.example.demo.model.Patient;
import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findAllByDateOfBirthBetween(Date begin, Date end);

    List<Patient> findAllByAdmittedBy_Department(Department department);

    List<Patient> findAllByAdmittedBy_Status(Status status);
}
