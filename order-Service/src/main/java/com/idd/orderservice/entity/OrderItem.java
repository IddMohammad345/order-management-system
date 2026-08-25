package com.idd.orderservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "ORDER_ITEMS")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @SequenceGenerator(name = "order_item_seq_generator",sequenceName = "ORDER_ITEM_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "order_item_seq_generator",strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID",nullable = false)
    private Order order;

    @Column(name = "PRODUCT_ID",nullable = false)
    private Long productId;

    @Column(name = "PRODUCT_NAME",nullable = false)
    private String productName;

    @Column(name = "SKU",nullable = false)
    private String sku;

    @Column(name = "QUANTITY",nullable = false)
    private Integer quantity;

    @Column(name = "UNIT_PRICE",nullable = false,precision = 14,scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "TOTAL_PRICE",nullable = false,precision = 14,scale = 2)
    private BigDecimal totalPrice;
}
