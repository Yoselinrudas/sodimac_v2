package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "naturalclient_document", foreignKey = @ForeignKey(name = "fk_ticket_naturalclient_numberdoc"))
    private NaturalClient naturalClient;

    @ManyToOne
    @JoinColumn(name = "proforma_id", foreignKey = @ForeignKey(name = "fk_ticket_proforma_id"))
    private Proforma proforma;
}
