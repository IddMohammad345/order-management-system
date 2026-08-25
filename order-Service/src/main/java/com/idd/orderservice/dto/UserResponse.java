package com.idd.orderservice.dto;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String status
) {
}
