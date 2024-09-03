package com.brixton.sodimac_v2.data.model;

import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@MappedSuperclass
public class Audit {

    @Column(name = "createdat")
    private LocalDateTime createdAt;
    @Column(name = "createdby", length = 20)
    private String createdBy;
    @Column(name = "updatedat")
    private LocalDateTime updatedAt;
    @Column(name = "updatedby", length = 20)
    private String updatedBy;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "registry_state")
    private RegistryStateType registryState;
}
