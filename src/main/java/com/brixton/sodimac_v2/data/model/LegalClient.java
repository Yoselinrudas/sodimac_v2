package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LEGAL_CLIENT")
@Getter
@Setter
@ToString(callSuper = true)
public class LegalClient extends Person{

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 11)
    private String ruc;

    @Column(name = "nombre/razonsocial",length = 50)
    private String razonSocial;

    private boolean supplier;
}
