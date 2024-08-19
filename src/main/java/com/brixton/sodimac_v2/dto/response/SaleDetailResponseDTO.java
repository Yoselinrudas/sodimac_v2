package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaleDetailResponseDTO extends AuditResponseDTO {

    private long product;
    private double priceSale;
    private float quantity;
    private double total;
    private String statusDetail;
}
