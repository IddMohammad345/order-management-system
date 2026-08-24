package com.idd.user_service.dto;

public record ErrorResponse(
        int status,
        String message
) {
}
