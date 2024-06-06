package com.anjoe.SpringJPA.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class StudentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int studentId;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Person is not born yet")
    private LocalDate dateOfBirth;

    private int teacherId;
}
