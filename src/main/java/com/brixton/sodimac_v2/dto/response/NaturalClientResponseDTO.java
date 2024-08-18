package com.brixton.sodimac_v2.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaturalClientResponseDTO extends PersonResponseDTO{

    private String name;
    private String lastName;
    private String document;
    private String number;
}
