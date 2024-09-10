package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 50)
    private String employeeName;

    @Column(name="lastname", length = 50)
    private String employeeLastname;

    @ManyToOne
    @JoinColumn(name = "area_id", foreignKey = @ForeignKey(name = "fk_employee_area_id"))
    private Area area;

    @Transient
    private int profileId;
}
