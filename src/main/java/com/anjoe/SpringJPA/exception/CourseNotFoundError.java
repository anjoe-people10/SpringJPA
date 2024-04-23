package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No such course exists")
public class CourseNotFoundError extends RuntimeException{

    public CourseNotFoundError() {
        super("No such course exists");
    }

}
