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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void get_test() {
    }

    @Test
    void create() {
    }

    @Test
    void findByStatus() {
    }

    @Test
    void findByDepartment() {
    }

    @Test
    void changeStatus() {
    }

    @Test
    void changeDepartment() {
    }
}