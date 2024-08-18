package com.brixton.sodimac_v2.service.mapper;

import com.brixton.sodimac_v2.data.model.Employee;
import com.brixton.sodimac_v2.dto.request.EmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.request.UpdateEmployeeRequestDTO;
import com.brixton.sodimac_v2.dto.response.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "area", target = "area.id")
    @Mapping(source = "perfil", target = "profileId")
    Employee employeeRequestDtoToEmployee(EmployeeRequestDTO employeeRequestDTO);

    @Mapping(source = "area.id", target = "area")
    @Mapping(source = "profileId", target = "perfil")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = "yyyy/MM/dd HH:mm")
    EmployeeResponseDTO employeeToEmployeeResponseDto(Employee employee);

    @Mapping(source = "area", target = "area.id")
    @Mapping(source = "perfil", target = "profileId")
    Employee updateEmployeeRequestDtoToEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDTO);
}
