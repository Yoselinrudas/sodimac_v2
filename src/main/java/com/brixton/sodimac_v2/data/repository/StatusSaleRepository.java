package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.enums.StatusGroupType;
import com.brixton.sodimac_v2.data.model.StatusSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusSaleRepository extends JpaRepository<StatusSale, Integer> {

    List<StatusSale> findByStatusGroup(StatusGroupType statusGroup);


}
