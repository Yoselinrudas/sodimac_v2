package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.enums.RegistryStateType;
import com.brixton.sodimac_v2.data.model.Category;
import com.brixton.sodimac_v2.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByRegistryState(RegistryStateType registryState);
    List<Product> findByCategory(Category category);
}
