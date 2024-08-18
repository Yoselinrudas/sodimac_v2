package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponseDTO extends AuditResponseDTO {


    private long id;
    private String nameProduct;
    private String category;
    private float quantity;
    private float minQuantity;
    private double priceSupplier;
    private double priceSale;
    private String codeProduct;
}
