package com.brixton.sodimac_v2.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MappedSuperclass
public class Person extends Audit{

    @Column(length = 50)
    private String address;

    @Column(length = 15)
    private String phone;

    @Column(length = 30)
    private String email;
}
