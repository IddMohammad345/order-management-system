package com.idd.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Table(name = "INVENTORY_TRANSACTION")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryTransaction {
    @Id
    @SequenceGenerator(name = "inventory_transaction_seq_generator",
    sequenceName = "INVENTORY_TRANSACTION_SEQ",
    allocationSize = 1)
    @GeneratedValue(generator = "inventory_transaction_seq_generator",strategy = GenerationType.SEQUENCE)
    @Column(name = "TRANSACTION_ID")
    public Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVENTORY_ID",nullable = false)
    private Inventory inventory;
    @Column(name = "PRODUCT_ID",nullable = false)
    private Long productId;
    @Column(name = "ORDER_ID")
    private Long orderId;
    @Column(name = "TRANSACTION_TYPE",nullable = false)
    private String transactionType;
    @Column(name = "QUANTITY",nullable = false)
    private Integer quantity;
    @Column(name = "CREATED_AT",nullable = false)
    private Instant createdAt;

    @PrePersist
    void OnCreate(){
        createdAt=Instant.now();
    }
}
