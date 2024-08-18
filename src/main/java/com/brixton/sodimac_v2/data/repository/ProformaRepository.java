package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Proforma;
import com.brixton.sodimac_v2.data.model.StatusSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProformaRepository extends JpaRepository<Proforma,Long> {

    List<Proforma> findAllByStatusSale(StatusSale statusSale);
    List<Proforma> findAllByStatusSale_Description(String description);
}
