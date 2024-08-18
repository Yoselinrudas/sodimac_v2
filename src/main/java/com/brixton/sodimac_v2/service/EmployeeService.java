package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.dto.request.EmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateEmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.response.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO createEmployee);
    List<EmployeeResponseDTO> createWithList(List<EmployeeRequestDTO> inputEmployees);
    EmployeeResponseDTO getEmployee(long id);
    List<EmployeeResponseDTO> getListEmployees();
    EmployeeResponseDTO updateEmployee(long id, UpdateEmployeeRequestDTO employeeForUpdate);
    void deleteEmployee(long id);
}
