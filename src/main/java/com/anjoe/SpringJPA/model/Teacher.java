package com.anjoe.SpringJPA.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id", updatable = false, nullable = false)
    private Integer teacherId;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @OneToMany(mappedBy = "classTeacher")
    @JsonManagedReference
    private List<Student> studentList;

}
