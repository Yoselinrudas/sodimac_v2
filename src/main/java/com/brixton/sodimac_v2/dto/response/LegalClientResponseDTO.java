package com.brixton.sodimac_v2.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LegalClientResponseDTO extends PersonResponseDTO{

    private String ruc;
    private String razonSocial;
    private boolean supplier;
}
