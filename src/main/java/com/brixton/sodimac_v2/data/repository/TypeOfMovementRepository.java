package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.TypeOfMovement;
import com.brixton.sodimac_v2.data.model.enums.StatusMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeOfMovementRepository extends JpaRepository<TypeOfMovement, Integer> {

    TypeOfMovement findBySubType(String subType);

}
