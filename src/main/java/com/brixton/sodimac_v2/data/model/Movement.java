package com.brixton.sodimac_v2.data.model;

import com.brixton.sodimac_v2.data.model.enums.TypeDocumentBusiness;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Movement extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_productxmovement"))
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "typemovement_id", foreignKey = @ForeignKey(name = "fk_typemovementxmovement"))
    private TypeOfMovement typeOfMovement;

    @Column(name = "affectedamount")
    private double affectedAmount;
    @Column(name = "newstock")
    private double newStock;
    @Column(name = "document_id")
    private int documentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "documentname", length = 20)
    private TypeDocumentBusiness typeDocumentBusiness;

    @Column(name = "datemovement")
    private LocalDate dateMovement;
}
