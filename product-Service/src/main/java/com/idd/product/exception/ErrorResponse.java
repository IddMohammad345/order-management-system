package com.idd.product.exception;

public record ErrorResponse(
        int statusCode,
        String message) {
}
