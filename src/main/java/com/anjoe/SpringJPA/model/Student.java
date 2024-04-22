package com.anjoe.SpringJPA.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Person is not born yet")
    private LocalDate dateOfBirth;
}
