package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.DataBusinessDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TicketResponseDTO extends DataBusinessDTO {

    private long id;
    private NaturalClientResponseDTO client;
    private double total;
    private List<SaleDetailResponseDTO> saleDetails;
}
