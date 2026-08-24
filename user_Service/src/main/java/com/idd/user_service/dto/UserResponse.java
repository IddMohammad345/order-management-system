package com.idd.user_service.dto;

import java.time.Instant;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String status,
        Instant createdAt
) {
}
