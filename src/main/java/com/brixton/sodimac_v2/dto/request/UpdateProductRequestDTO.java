package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDTO {

    @NotNull(message = "nameProduct no puede ser vacio")
    private String nameProduct;
    @NotNull(message = "Debe pertenecer a una categoria")
    private String category;
    @Digits(integer = 8, fraction = 2)
    private float quantity;
    @Digits(integer = 8, fraction = 2)
    private float minQuantity;
    @Digits(integer = 8, fraction = 2)
    @NotNull(message = "Debe tener un precio proveedor")
    private double priceSupplier;
    @Digits(integer = 8, fraction = 2)
    private double priceSale;
}
