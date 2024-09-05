package com.brixton.sodimac_v2.dto.response.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBusinessDTO extends AuditResponseDTO{
    private String razonSocialBusiness;
    private String rucBusiness;
    private String addressBusiness;
}
