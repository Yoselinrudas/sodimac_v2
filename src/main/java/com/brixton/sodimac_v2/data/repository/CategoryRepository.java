package com.brixton.sodimac_v2.data.repository;

import com.brixton.sodimac_v2.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}
