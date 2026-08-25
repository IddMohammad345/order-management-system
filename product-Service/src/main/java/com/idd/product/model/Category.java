package com.idd.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Table(name = "CATEGORY")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @SequenceGenerator(name = "category_seq_generator",sequenceName = "CATEGORY_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "category_seq_generator",strategy = GenerationType.SEQUENCE)
    @Column(name = "CATEGORY_ID")
    private Long id;
    @Column(name = "CATEGORY_NAME",nullable = false,unique = true,length = 100)
    private String name;
    @Column(name = "DESCRIPTION",length = 500)
    private String description;
    @Column(name = "STATUS",nullable = false)
    private String status;
    @Column(name = "CREATED_AT",nullable = false)
    private Instant createdAt;
    @Column(name = "UPDATED_AT",nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate(){
        Instant now=Instant.now();

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
