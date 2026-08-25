package com.idd.product.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
@Entity
@Table(name = "PRODUCT")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "product_seq_generator",sequenceName = "PRODUCT_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "product_seq_generator",strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUCT_ID")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(
            name = "CATEGORY_ID",
            nullable = false
    )
    private Category category;
    @Column(name = "PRODUCT_NAME",nullable = false,length = 150)
    private String name;
    @Column(name = "DESCRIPTION",length = 1000)
    private String description;
    @Column(name = "SKU",nullable = false,unique = true,length = 50)
    private String sku;
    @Column(name = "PRICE", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;
    @Column(name = "STATUS",nullable = false)
    private String status;
    @Column(name = "CREATED_AT",nullable = false)
    private Instant createdAt;
    @Column(name = "UPDATED_AT",nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate(){
        Instant now= Instant.now();
        createdAt=now;
        updatedAt=now;
        if (status==null){
            status="ACTIVE";
        }
    }
    @PreUpdate
    void onUpdate(){
        updatedAt=Instant.now();
    }
}
