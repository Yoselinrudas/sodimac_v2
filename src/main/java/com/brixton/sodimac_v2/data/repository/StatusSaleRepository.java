package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.enums.StatusGroupType;
import com.brixton.sodimac_v2.data.model.StatusSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StatusSaleRepository extends JpaRepository<StatusSale, Integer> {

    List<StatusSale> findByStatusGroup(StatusGroupType statusGroup);
    Optional<StatusSale> findByIdAndStatusGroup(Integer id, StatusGroupType statusGroup);
    List<StatusSale> findByDescription(String description);

}
