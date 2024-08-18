package com.brixton.sodimac_v2.data.controller;

import com.brixton.sodimac_v2.dto.request.EmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateEmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.response.EmployeeResponseDTO;
import com.brixton.sodimac_v2.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/management/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO inputEmployee){
        EmployeeResponseDTO employee = employeeService.createEmployee(inputEmployee);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/createWithList")
    public  ResponseEntity<List<EmployeeResponseDTO>> createWithList(@RequestBody List<EmployeeRequestDTO> inputEmployees){

        List<EmployeeResponseDTO>employees = employeeService.createWithList(inputEmployees);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable long id){
        //EmployeeResponseDTO employeeFound = employeeService.getEmployee(id);
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getListEmployees(){
        return new ResponseEntity<>(employeeService.getListEmployees(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@Valid @PathVariable long id, @RequestBody UpdateEmployeeRequestDTO employeeForUpdate) {
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeForUpdate));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
