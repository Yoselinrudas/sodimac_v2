package com.brixton.sodimac_v2.data.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Proforma extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_proforma_employee_id"))
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "statussale_id", foreignKey = @ForeignKey(name = "fk_proforma_statussale_id"))
    private StatusSale statusSale;

    private float total;
    @OneToMany(mappedBy = "proforma")
    private List<SaleDetail> details;
}
