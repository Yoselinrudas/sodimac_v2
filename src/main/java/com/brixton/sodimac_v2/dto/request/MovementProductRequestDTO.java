package com.brixton.sodimac_v2.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovementProductRequestDTO {

    @NotNull(message = "productId no puede ser vacio")
    private long productId;
    @NotNull(message = "Debe pertenecer a un typeOfMovement")
    private int typeOfMovement;
    @NotNull(message = "Amount no debe ser vacio")
    @Digits(integer = 8, fraction = 2)
    private double affectedAmount;
    @NotNull(message = "documentId no debe ser vacio")
    private int documentId;
    @NotNull(message = "Debe pertenecer a un typeDocument")
    private String typeDocumentBusiness;
    @NotNull(message = "fecha no debe ser vacio")
    @PastOrPresent
    private String dateMovement;

}
