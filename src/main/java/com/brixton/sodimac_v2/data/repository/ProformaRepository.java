package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Proforma;
import com.brixton.sodimac_v2.data.model.StatusSale;
import com.brixton.sodimac_v2.data.model.enums.RegistryProformaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProformaRepository extends JpaRepository<Proforma,Long> {

    List<Proforma> findAllByStatusSale(StatusSale statusSale);

    //consulta directa y que no permita una consulta perezosa con transaccional
    @Query("SELECT SUM(detail.quantity) FROM Proforma p " +
            "JOIN p.details detail " +
            "WHERE detail.product.id = :productId " +
            "AND p.statusSale = :statusSale " +
            "AND p.registryProforma = :registryProformaType")
    Double findConfirmedQuantityForProduct(@Param("productId") long productId,
                                           @Param("statusSale") StatusSale statusSale,
                                           @Param("registryProformaType") RegistryProformaType registryProformaType);

    @Query("SELECT p FROM Proforma p JOIN FETCH p.details WHERE p.id = :id")
    Optional<Proforma> findByIdWithDetails(@Param("id") Long id);
}
