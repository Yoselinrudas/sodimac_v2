package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.DataBusinessDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BillResponseDTO extends DataBusinessDTO {

    private long id;
    private LegalClientResponseDTO client;
    private List<SaleDetailResponseDTO> salesDetails;
    private double total;
    private double subTotal;
    private double igv;
}
