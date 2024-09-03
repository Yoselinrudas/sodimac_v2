package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TicketResponseDTO extends AuditResponseDTO {

    private String razonSocialBusiness;
    private String rucBusiness;
    private String address;
    private NaturalClientResponseDTO client;
    private double total;
    private List<SaleDetailResponseDTO> saleDetails;
}
