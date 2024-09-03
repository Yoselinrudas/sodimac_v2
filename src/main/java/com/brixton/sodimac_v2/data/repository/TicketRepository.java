package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
