package com.idd.product.repository;

import com.idd.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findBySku(String sku);
    Boolean existsBySku(String sku);
}
