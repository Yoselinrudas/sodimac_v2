package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequestDTO {

    @NotNull(message = "Cliente no debe ser vacio")
    private LegalClientRequestDTO client;
    @NotNull(message = "El id de la proforma no debe ser vacio")
    private long proformaId;
}
