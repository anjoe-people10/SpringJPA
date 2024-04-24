package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String entity) {
        super("No Such " + entity + " Exists");
    }

}
