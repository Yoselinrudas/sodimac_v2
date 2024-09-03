package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BillResponseDTO extends AuditResponseDTO {
    private String razonSocialBusiness;
    private String rucBusiness;
    private String addressBusiness;
    private LegalClientResponseDTO client;
    private List<SaleDetailResponseDTO> salesDetails;
    private double total;
    private double subTotal;
    private double igv;
}
