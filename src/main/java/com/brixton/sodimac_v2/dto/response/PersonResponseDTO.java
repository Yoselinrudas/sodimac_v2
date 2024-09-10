package com.brixton.sodimac_v2.dto.response;

import com.brixton.sodimac_v2.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonResponseDTO extends AuditResponseDTO {
    private String address;
    private String phone;
    private String email;
}
