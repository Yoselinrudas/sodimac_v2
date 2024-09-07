package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Integer> {
}
