package com.anjoe.SpringJPA.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GetErrorResponse {

    public static ResponseEntity<?> studentNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("No such student")
                        .build());
    }

    public static ResponseEntity<ErrorResponse> studentAlreadyExists() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message("Student already exists")
                        .build());
    }
}
