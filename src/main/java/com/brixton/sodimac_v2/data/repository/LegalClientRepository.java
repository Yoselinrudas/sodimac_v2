package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.LegalClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalClientRepository extends JpaRepository<LegalClient, String> {

    List<LegalClient> findByRegistryState(RegistryStateType registryState);
}
