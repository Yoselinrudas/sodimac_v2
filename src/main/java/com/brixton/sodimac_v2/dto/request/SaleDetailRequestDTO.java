package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaleDetailRequestDTO {

    @NotNull(message = "product no puede ser vacio")
    private long product;
    @NotNull(message = "Quantity no puede ser vacio")
    private float quantity;

}
