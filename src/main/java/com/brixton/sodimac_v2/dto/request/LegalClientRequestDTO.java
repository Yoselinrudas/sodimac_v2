package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LegalClientRequestDTO extends PersonRequestDTO{

    @NotNull(message = "Ruc no puede ser vacio")
    private String ruc;
    @NotNull(message = "Razon social no puede ser vacio")
    private String razonSocial;

    private boolean supplier;
}
