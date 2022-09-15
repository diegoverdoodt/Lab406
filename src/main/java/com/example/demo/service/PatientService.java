package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Patient;
import com.example.demo.model.Status;

import java.util.Date;
import java.util.List;

public interface PatientService {

    Patient get(String id);

    Patient save(Patient patient);

    Patient update(String id, Patient patient);
    Patient updatePatient(Patient patient);

    void delete(Patient patient);

    List<Patient> list();

    List<Patient> findPatientBetweenBirth(Date begin, Date end);

    List<Patient> findPatientsDepartmentAdmittedBy(Department department);

    List<Patient> findPatientsAdmittedStatus(Status status);
}
