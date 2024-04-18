package com.anjoe.SpringJPA.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class StudentResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;
}
