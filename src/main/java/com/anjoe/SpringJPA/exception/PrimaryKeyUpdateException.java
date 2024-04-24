package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PrimaryKeyUpdateException extends RuntimeException {

    public PrimaryKeyUpdateException() {
        super("Primary Key Cannot Be Changed");
    }
}
