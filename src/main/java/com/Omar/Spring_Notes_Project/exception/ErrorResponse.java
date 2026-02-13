package com.Omar.Spring_Notes_Project.exception;

public record ErrorResponse(
        int status,
        String error,
        String message
) {
}
