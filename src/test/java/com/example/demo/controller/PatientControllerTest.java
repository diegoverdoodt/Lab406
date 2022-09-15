package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Patient;
import com.example.demo.model.Status;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PatientRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Employee employee1 = new Employee(123456, Department.cardiology,"Rodrigo Cerrado", Status.ON );
    Employee employee2 = new Employee(654321, Department.orthopaedic,"Rodrigo Abierto", Status.OFF );

    private Date date1 = new Date();
    private Date date2 = new Date();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Patient patient1 = new Patient(7, "Anabel cien", date1, employee1);
        Patient patient2 = new Patient(8, "Anabel Mil", date2, employee2);
        patientRepository.saveAll(List.of(patient1, patient2));

        Employee employee1 = new Employee(123456, Department.cardiology,"Rodrigo Cerrado", Status.ON );
        Employee employee2 = new Employee(654321, Department.orthopaedic,"Rodrigo Abierto", Status.OFF );
        employeeRepository.saveAll(List.of(employee1,employee2));
    }

    @AfterEach
    void tearDown() {
        patientRepository.deleteAll();
    }

    @Test
    void list() throws Exception{

        MvcResult mvcResult = mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Patient> patients = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Patient>>() {});

        assertEquals(2, patients.size());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Anabel cien"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Anabel Mil"));
    }

    @Test
    void getTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/patients/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Patient patient = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Patient.class);

        assertEquals(patient.getPatientId(), 7);
        assertEquals(patient.getAdmittedBy(), employee1);
        assertEquals(patient.getName(),"Anabel cien");
        assertEquals(patient.getDateOfBirth(),date1);
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