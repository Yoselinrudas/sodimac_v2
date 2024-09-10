package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProformaResponseDTO extends AuditResponseDTO {

    private long id;
    private long employee;
    private int statusSale;
    private double total;
    private List<SaleDetailResponseDTO> details;
}
