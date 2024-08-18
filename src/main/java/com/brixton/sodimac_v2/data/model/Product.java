package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@ToString(callSuper = true)
public class Product extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",foreignKey = @ForeignKey(name = "fk_product_category_id"))
    private Category category;

    private float quantity;

    @Column(name = "min_quantity")
    private float minQuantity;

    @Column(name = "pricesupplier")
    private double priceSupplier;

    @Column(name = "pricesale")
    private double priceSale;

    @Column(name = "codeproduct",length = 20)
    private String codeProduct;
}
