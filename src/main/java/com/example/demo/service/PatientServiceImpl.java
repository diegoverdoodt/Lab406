package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.model.Patient;
import com.example.demo.model.Status;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Patient get(String id) {
        Integer intId = Integer.parseInt(id);
        return patientRepository.findById(intId).get();
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient){

        Integer idPatient = patient.getPatientId();
        Patient storedPatient = get(idPatient.toString());
        storedPatient.setDateOfBirth(patient.getDateOfBirth());
        storedPatient.setAdmittedBy(patient.getAdmittedBy());
        storedPatient.setName(patient.getName());

        return patientRepository.save(storedPatient);
    }

    @Override
    public Patient update(String id, Patient patient) {
        return null;
    }

    @Override
    public void delete(Patient patient) {

    }

    @Override
    public List<Patient> list() {
        return patientRepository.findAll();
    }

    public List<Patient> findPatientBetweenBirth(Date begin, Date end) {
        return patientRepository.findAllByDateOfBirthBetween(begin, end);
    }

    @Override
    public List<Patient> findPatientsDepartmentAdmittedBy(Department department){
        return patientRepository.findAllByAdmittedBy_Department(department);
    }

    @Override
    public List<Patient> findPatientsAdmittedStatus(Status status){
        return patientRepository.findAllByAdmittedBy_Status(status);
    }
}
