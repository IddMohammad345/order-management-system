package com.idd.user_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "USERs")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(
            generator = "user_seq_generator",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "user_seq_generator",
            sequenceName = "USER_SEQ",
            allocationSize = 1
    )
    @Column(name = "USER_ID", length = 20)
    private Long id;
    @Column(name = "FIRST_NAME", length = 100,nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 100)
    private String lastName;
    @Column(name = "EMAIL", length = 150,unique = true,nullable = false)
    @Email
    private String email;
    @Column(name = "PHONE", length = 20)
    private String phone;
    @Column(name = "PASSWORD", length = 255)
    private String password;
    @Column(name = "STATUS", length = 20)
    private String status;
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAT;
    @Column(name = "UPDATED_AT", nullable = false)
    private Instant updatedAT;

    @PrePersist
    void OnCreate() {
        Instant now = Instant.now();

        createdAT = now;
        updatedAT = now;
        if (status == null) {
            status = "ACTIVE";
        }
    }

    @PreUpdate
    void OnUpdate() {
        updatedAT = Instant.now();
    }


}
