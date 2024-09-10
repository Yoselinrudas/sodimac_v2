package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT b FROM Bill b JOIN FETCH b.proforma p JOIN FETCH p.details WHERE b.id = :id")
    Optional<Bill> findByIdWithDetails(@Param("id") Long id);
}
