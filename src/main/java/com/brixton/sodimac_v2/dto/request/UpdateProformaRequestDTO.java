package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateProformaRequestDTO {
    private int statusSale;
    @NotNull(message = "SaleDetal no puede estar vacio")
    private List<SaleDetailRequestDTO> details;
}
