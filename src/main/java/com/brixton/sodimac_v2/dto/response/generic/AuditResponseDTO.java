package com.brixton.sodimac_v2.dto.response.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditResponseDTO {

    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
    private String registryState;
}
