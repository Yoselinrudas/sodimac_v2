package com.brixton.sodimac_v2.controller;

import com.brixton.sodimac_v2.dto.request.EmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.response.EmployeeResponseDTO;
import com.brixton.sodimac_v2.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createEmployee() {
        //AAA
        //Arrange  (Inicializa)
        EmployeeRequestDTO request = new EmployeeRequestDTO();
        EmployeeResponseDTO response = new EmployeeResponseDTO();
        when(employeeService.createEmployee(any(EmployeeRequestDTO.class))).thenReturn(response);

        //Act (Realiza)
        ResponseEntity<EmployeeResponseDTO> result = employeeController.createEmployee(request);

        //Assert (Comprueba)
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void createWithList() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void getListEmployees() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}