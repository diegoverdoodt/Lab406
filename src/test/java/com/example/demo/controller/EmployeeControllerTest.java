package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Status;
import com.example.demo.repository.EmployeeRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeRepository employeeRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Employee employee1 = new Employee(123456, Department.cardiology,"Rodrigo Cerrado", Status.ON );
        Employee employee2 = new Employee(654321, Department.orthopaedic,"Rodrigo Abierto", Status.OFF );

        employeeRepository.saveAll(List.of(employee1, employee2));
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void list() throws Exception{

        MvcResult mvcResult = mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Employee> employees = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Employee>>() {});

        assertEquals(2, employees.size());

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Rodrigo Cerrado"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Rodrigo Abierto"));
    }

    @Test
    void get_test() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/employees/123456"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Employee employee = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);

        assertEquals(employee.getEmployeeId(), 123456);
        assertEquals(employee.getDepartment(),Department.cardiology);
        assertEquals(employee.getStatus(),Status.ON);
        assertEquals(employee.getName(),"Rodrigo Cerrado");
    }

    @Test
    void create() throws Exception{
        Employee newEmployee = new Employee(987654, Department.psychiatric,"Amador Abierto", Status.ON_CALL );
        String payload = objectMapper.writeValueAsString(newEmployee);
        MvcResult mvcResult = mockMvc.perform(post("/employees/create/")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Employee employee = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);
        assertEquals(employee.getEmployeeId(), 987654);
        assertEquals(employee.getDepartment(),Department.psychiatric);
        assertEquals(employee.getStatus(),Status.ON_CALL);
        assertEquals(employee.getName(),"Amador Abierto");

        assertTrue(employeeRepository.findById(987654).isPresent());


    }

    @Test
    void findByStatus() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/employees/status/ON"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        Employee employee3 = new Employee(654321, Department.orthopaedic,"Rodrigo Abierto", Status.ON );
        employeeRepository.save(employee3);
        List<Employee> employees = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Employee>>() {});



        assertEquals(2, employees.size());

    }

    @Test
    void findByDepartment() {
    }

    @Test
    void changeStatus() throws Exception{
        Employee newEmployee = employeeRepository.findById(123456).get();
        newEmployee.setStatus(Status.OFF);
        String payload = objectMapper.writeValueAsString(newEmployee);
        MvcResult mvcResult = mockMvc.perform(patch("/employees/123456/change/status")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Employee employeeUpdate = employeeRepository.findById(123456).get();

        assertEquals(employeeUpdate.getStatus(), Status.OFF);
    }

    @Test
    void changeDepartment() throws Exception{

        Employee newEmployee = employeeRepository.findById(123456).get();
        newEmployee.setDepartment(Department.immunology);
        String payload = objectMapper.writeValueAsString(newEmployee);
        MvcResult mvcResult = mockMvc.perform(patch("/employees/123456/change/department")
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Employee employeeUpdate = employeeRepository.findById(123456).get();

        assertEquals(employeeUpdate.getDepartment(), Department.immunology);
    }
}