package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovementProductResponseDTO extends AuditResponseDTO {

    private long productId;
    private int typeOfMovement;
    private double affectedAmount;
    private double newStock;
    private int documentId;
    private String typeDocumentBusiness;
    private String dateMovement;
}
