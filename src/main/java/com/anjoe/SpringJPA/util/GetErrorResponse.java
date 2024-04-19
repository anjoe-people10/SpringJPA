package com.anjoe.SpringJPA.util;

import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CONFLICT;

public class GetErrorResponse {

    public static ErrorResponse studentNotFound() {
        return ErrorResponse.builder()
                        .status(CONFLICT)
                        .message("No such student")
                        .build();
    }

    public static ErrorResponse studentAlreadyExists() {
        return ErrorResponse.builder()
                        .status(CONFLICT)
                        .message("Student already exists")
                        .build();
    }
}
