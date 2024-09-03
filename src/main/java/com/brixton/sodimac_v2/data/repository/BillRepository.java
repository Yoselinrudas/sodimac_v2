package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
