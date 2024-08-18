package com.brixton.sodimac_v2.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeResponseDTO extends PersonResponseDTO {

    private String employeeName;
    private String employeeLastname;
    private String area;
    private String perfil;

}
