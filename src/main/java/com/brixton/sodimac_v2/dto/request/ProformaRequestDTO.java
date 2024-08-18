package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProformaRequestDTO {

    @NotNull(message = "employee no puede ser null")
    private long employee;
    private int statusSale;
    @NotNull(message = "SaleDetal no puede estar vacio")
    private List<SaleDetailRequestDTO> details;
}
