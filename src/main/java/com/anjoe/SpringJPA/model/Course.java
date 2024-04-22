package com.anjoe.SpringJPA.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Course {
    @Id
    private int courseId;

    @NotBlank(message = "Course code cannot be null")
    private String courseCode;

    @NotBlank(message = "Course name cannot be null")
    private String courseName;
}
