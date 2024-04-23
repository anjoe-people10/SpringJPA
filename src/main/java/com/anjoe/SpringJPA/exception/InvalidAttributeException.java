package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid attributes specified")
public class InvalidAttributeException extends RuntimeException{
    public InvalidAttributeException() {
        super("Invalid attributes specified");
    }
}
