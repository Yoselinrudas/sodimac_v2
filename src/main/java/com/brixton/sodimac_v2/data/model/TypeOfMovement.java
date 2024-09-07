package com.brixton.sodimac_v2.data.model;

import com.brixton.sodimac_v2.data.model.enums.StatusMovement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "typemovement")
public class TypeOfMovement extends Audit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subtype", length = 30)
    private  String subType;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusmovement", length = 20)
    private StatusMovement statusMovement;
}
