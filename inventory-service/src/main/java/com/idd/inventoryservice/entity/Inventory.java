package com.idd.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Table(name = "INVENTORY")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
    @Id
    @SequenceGenerator(name = "inventory_seq_generator",sequenceName = "INVENTORY_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "inventory_seq_generator",strategy = GenerationType.SEQUENCE)
    @Column(name = "INVENTORY_ID")
    private Long id;
    @Column(name = "PRODUCT_ID",nullable = false,unique = true)
    private Long productId;
    @Column(name = "AVAILABLE_QUANTITY",nullable = false)
    private Integer availableQuantity;
    @Column(name = "RESERVED_QUANTITY",nullable = false)
    private Integer reservedQuantity;
    @Column(name = "STATUS",nullable = false)
    private String status;
    @Column(name = "CREATED_AT",nullable = false)
    private Instant createdAt;
    @Column(name = "UPDATED_AT",nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreated(){
        Instant now=Instant.now();
        createdAt=now;
        updatedAt=now;

        if (reservedQuantity==null){
            reservedQuantity=0;
        }
        if (status==null){
            status="ACTIVE";
        }
    }

    @PreUpdate
    void onUpdate(){
        updatedAt=Instant.now();
    }
}
