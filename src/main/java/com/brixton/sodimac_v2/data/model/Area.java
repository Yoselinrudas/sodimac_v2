package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Area extends Audit{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;
    @Column(name = "name", length = 20)
    private String areaName;
}
