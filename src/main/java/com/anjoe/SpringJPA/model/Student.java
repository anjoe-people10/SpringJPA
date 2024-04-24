package com.anjoe.SpringJPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Person is not born yet")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher classTeacher;
}
