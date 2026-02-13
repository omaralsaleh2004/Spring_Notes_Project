package com.Omar.Spring_Notes_Project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),           // 404
                status.getReasonPhrase(), // "Not Found"
                ex.getMessage()           // e.g. "Note not found"
        );

        return new ResponseEntity<>(errorResponse, status); // <-- pass both body and status
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleAll(InvalidDataException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(status.value(),status.getReasonPhrase() ,ex.getMessage()),
                status);
    }

    @ExceptionHandler(HandelAllException.class)
    public ResponseEntity<ErrorResponse> handleAll(HandelAllException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(status.value(),status.getReasonPhrase() ,"Something went wrong"),
                status);
    }


}
