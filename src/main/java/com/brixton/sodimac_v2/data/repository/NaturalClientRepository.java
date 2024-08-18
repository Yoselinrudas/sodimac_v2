package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.NaturalClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NaturalClientRepository extends JpaRepository<NaturalClient, String>{

    List<NaturalClient> findByRegistryState(RegistryStateType registryState);
}
