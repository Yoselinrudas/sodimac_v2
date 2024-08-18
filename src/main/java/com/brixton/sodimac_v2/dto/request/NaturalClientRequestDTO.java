package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaturalClientRequestDTO extends PersonRequestDTO{

    @NotNull(message = "Nombres no puede ser vacio")
    private String name;
    @NotNull(message = "Apellidos no puede ser vacio")
    private String lastName;
    @NotNull(message = "Tipo de Documento requerido")
    private String document;
    @NotNull(message = "Numero de documento requerido")
    private String number;
}
