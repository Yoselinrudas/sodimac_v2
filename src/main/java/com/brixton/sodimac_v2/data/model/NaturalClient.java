package com.brixton.sodimac_v2.data.model;

import com.brixton.sodimac_v2.data.model.enums.TypeDocument;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "NATURAL_CLIENT")
@Getter
@Setter
@ToString(callSuper = true)
public class NaturalClient extends Person{

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "documentnumber", length = 20)
    private String documentNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "typedocument_id")
    private TypeDocument typeDocument;

    @Column(length = 50)
    private String name;

    @Column(name = "lastname", length = 50)
    private String lastName;
}
