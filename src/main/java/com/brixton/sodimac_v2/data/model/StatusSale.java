package com.brixton.sodimac_v2.data.model;

import com.brixton.sodimac_v2.data.enums.StatusGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "statussale")
@Getter
@Setter
@ToString
public class StatusSale extends Audit{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusgroup", length = 20)
    private StatusGroupType statusGroup;
}
