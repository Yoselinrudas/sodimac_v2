package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t JOIN FETCH t.proforma p JOIN FETCH p.details WHERE t.id = :id")
    Optional<Ticket> findByIdWithDetails(@Param("id") Long id);

}
