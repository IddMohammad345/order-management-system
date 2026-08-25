package com.idd.product.repository;

import com.idd.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByNameIgnoreCase(String name);
    Boolean existsByNameIgnoreCase(String name);
}
