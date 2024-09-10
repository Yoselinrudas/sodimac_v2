package com.brixton.sodimac_v2.service.utils;

import com.brixton.sodimac_v2.data.model.Audit;
import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.dto.response.generic.DataBusinessDTO;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class FuntionalBusinessInterfaces {

    private FuntionalBusinessInterfaces() {
    }

    public static final Consumer<Audit> auditCreation = input->{
        input.setCreatedAt(LocalDateTime.now());
        input.setCreatedBy(Constantes.USER_APP);
        input.setRegistryState(RegistryStateType.ACTIVE);
    };
    public static final Consumer<Audit> auditUpdate = input ->{
        input.setUpdatedBy(Constantes.USER_APP);
        input.setUpdatedAt(LocalDateTime.now());
    };

    public static final Consumer<DataBusinessDTO> business = input->{
        input.setRazonSocialBusiness(Constantes.RAZON_SOCIAL);
        input.setAddressBusiness(Constantes.ADDRESS);
        input.setRucBusiness(Constantes.RUC);
    };

    public static final Consumer<Audit> auditDelete = input->{
        input.setUpdatedBy(Constantes.USER_APP);
        input.setUpdatedAt(LocalDateTime.now());
        input.setRegistryState(RegistryStateType.INACTIVE);
    };
}
