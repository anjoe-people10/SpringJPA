package com.anjoe.SpringJPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="Course already exists")
public class CourseAlreadyExistError extends RuntimeException{

    public CourseAlreadyExistError() {
        super("Course already exists");
    }

}
