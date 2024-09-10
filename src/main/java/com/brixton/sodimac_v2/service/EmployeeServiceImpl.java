package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.data.controller.GenericNotFoundException;
import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.Area;
import com.brixton.sodimac_v2.data.model.Employee;
import com.brixton.sodimac_v2.data.repository.AreaRepository;
import com.brixton.sodimac_v2.data.repository.EmployeeRepository;
import com.brixton.sodimac_v2.dto.request.EmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateEmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.response.EmployeeResponseDTO;
import com.brixton.sodimac_v2.service.mapper.EmployeeMapper;
import com.brixton.sodimac_v2.service.utils.ConstanteError;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ToString
public class EmployeeServiceImpl implements EmployeeService{

    private static final String USER_APP = "BRIXTON";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO createEmployee) {
        Employee employee = EmployeeMapper.INSTANCE.employeeRequestDtoToEmployee(createEmployee);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setCreatedBy(USER_APP);
        employee.setRegistryState(RegistryStateType.ACTIVE);
        Area area = areaRepository.findById(employee.getArea().getId()).orElseThrow(() -> new GenericNotFoundException(("Area con Id no existente")));
        employee.getArea().setAreaName((area.getAreaName()));
        log.info("Employee::: {}", employee);
        employeeRepository.save(employee);
        EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employee);
        log.info("EmployeeResponseDTO: {}", employeeResponseDTO);
        return employeeResponseDTO;
    }

    @Override
    public List<EmployeeResponseDTO> createWithList(List<EmployeeRequestDTO> inputEmployees) {
        List<EmployeeResponseDTO> outputEmployees = new ArrayList<>();
        for(EmployeeRequestDTO employeeRequestDTO: inputEmployees){
            try{
                outputEmployees.add(createEmployee(employeeRequestDTO));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return outputEmployees;
    }

    @Override
    public EmployeeResponseDTO getEmployee(long id) {
        Employee employeeFound = employeeRepository.findById(id).orElseThrow(() -> new GenericNotFoundException((ConstanteError.EMPLOYEE_NOT_FOUND)));
        return EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employeeFound);
    }

    @Override
    public List<EmployeeResponseDTO> getListEmployees() {
        List<EmployeeResponseDTO> activeEmployees = new ArrayList<>();
        List<Employee> employeeFounds = employeeRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for (Employee employeeFound : employeeFounds) {
            activeEmployees.add(EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employeeFound));
        }
        return activeEmployees;
    }

    @Override
    public EmployeeResponseDTO updateEmployee(long id, UpdateEmployeeRequestDTO employeeForUpdate) {
        Employee original = employeeRepository.findById(id).orElseThrow(() -> new GenericNotFoundException((ConstanteError.EMPLOYEE_NOT_FOUND)));
        Employee employeeTemp = EmployeeMapper.INSTANCE.updateEmployeeRequestDtoToEmployee(employeeForUpdate);

        original.setEmployeeName(employeeTemp.getEmployeeName());
        original.setEmployeeLastname(employeeTemp.getEmployeeLastname());
        original.setArea(employeeTemp.getArea());
        original.setProfileId(employeeTemp.getProfileId());
        original.setAddress(employeeTemp.getAddress());
        original.setPhone(employeeTemp.getPhone());

        original.setUpdatedAt(LocalDateTime.now());
        log.info(original.getUpdatedAt().toString());
        original.setUpdatedBy(USER_APP);
        Area area = areaRepository.findById(employeeTemp.getArea().getId()).orElseThrow(() -> new GenericNotFoundException(("Area con Id no existente")));
        employeeTemp.getArea().setAreaName(area.getAreaName());
        original.setArea(employeeTemp.getArea());
        employeeRepository.save(original);
        return EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(original);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employeeFound = employeeRepository.findById(id).orElseThrow(() -> new GenericNotFoundException(ConstanteError.EMPLOYEE_NOT_FOUND));
        employeeFound.setRegistryState(RegistryStateType.INACTIVE);
        employeeFound.setUpdatedAt(LocalDateTime.now());
        employeeFound.setUpdatedBy(USER_APP);
        employeeRepository.save(employeeFound);

    }
}
