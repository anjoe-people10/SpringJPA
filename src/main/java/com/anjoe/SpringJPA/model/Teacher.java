package com.anjoe.SpringJPA.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @OneToMany(mappedBy = "classTeacher")
    private List<Student> studentList;

}
