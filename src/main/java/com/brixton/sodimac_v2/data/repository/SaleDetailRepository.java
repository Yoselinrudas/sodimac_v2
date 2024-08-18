package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.SaleDetail;
import com.brixton.sodimac_v2.data.model.StatusSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {
    //List<SaleDetail> findbyStatusSale(StatusSale statusSale);
}
