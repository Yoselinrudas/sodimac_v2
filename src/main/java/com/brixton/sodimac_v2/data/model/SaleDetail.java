package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SALE_DETAIL")
@Getter
@Setter
@ToString(callSuper = true)
public class SaleDetail extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_saledetail_product"))
    private Product product;

    @Column(name = "price_sale")
    private double priceSale;
    private float quantity;
    private double total;

    @ManyToOne
    @JoinColumn(name = "statusdetail_id", foreignKey = @ForeignKey(name = "fk_saledetail_statusdetail_id"))
    private StatusSale statusSale;

    @ManyToOne
    @JoinColumn(name = "proforma_id", foreignKey = @ForeignKey(name = "fk_saledetail_proforma_id"))
    private Proforma proforma;
}
