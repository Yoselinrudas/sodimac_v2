package com.brixton.sodimac_v2.service.utils;

import com.brixton.sodimac_v2.data.model.Audit;
import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class FuntionalBusinessInterfaces {

    public static Consumer<Audit> auditCreation = input->{
        input.setCreatedAt(LocalDateTime.now());
        input.setCreatedBy(Constantes.USER_APP);
        input.setRegistryState(RegistryStateType.ACTIVE);
    };

}
