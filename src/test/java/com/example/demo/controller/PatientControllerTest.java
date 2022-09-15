package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Patient;
import com.example.demo.model.Status;
import com.example.demo.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PatientRepository patientRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Employee employee1 = new Employee(123456, Department.cardiology,"Rodrigo Cerrado", Status.ON );
        Employee employee2 = new Employee(654321, Department.orthopaedic,"Rodrigo Abierto", Status.OFF );

        //Patient patient1 = new Patient(7, "Anabel cien", Date.parse("2000-05-18"), 123456);
    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    void list() {
    }

    @Test
    void get() {
    }

    @Test
    void findAllByDateOfBirthIsBetween() {
    }

    @Test
    void findPatientsDepartmentAdmittedBy() {
    }

    @Test
    void findPatientsAdmittedStatusOFF() {
    }

    @Test
    void create() {
    }

    @Test
    void changeDepartment() {
    }
}