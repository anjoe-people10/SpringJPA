package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Primary key cannot be modified")
public class PrimaryKeyUpdateError extends RuntimeException{

    public PrimaryKeyUpdateError() {
        super("Primary Key cannot be changed");
    }
}
