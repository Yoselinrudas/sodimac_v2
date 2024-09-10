package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeRequestDTO extends PersonRequestDTO{

    @NotNull(message = "Nombres no puede ser vacio")
    private String employeeName;

    @NotNull(message = "Apellidos no puede ser vacio")
    private String employeeLastname;

    @NotNull(message = "Debe pertenecer a un área")
    @Min(1)
    @Max(100)
    private String area;
    @NotNull(message = "Debe asociarse a un perfil")
    @Min(1)
    @Max(100)
    private String perfil;
}
