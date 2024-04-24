package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RecordAlreadyExistException extends RuntimeException {

    public RecordAlreadyExistException(String entity) {
        super(entity + " Already Exists");
    }

}
