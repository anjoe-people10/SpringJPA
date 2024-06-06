package com.anjoe.SpringJPA.DTO;

import com.anjoe.SpringJPA.dto.StudentDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void nameTestValid() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("John Doe");
        assertEquals(studentDTO.getName(), "John Doe");
    }

    @Test
    public void nameTestNull() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(null);
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void nameTestEmpty() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("");
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void nameTestBlank() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("   ");

        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void dateOfBirthTestValid() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setDateOfBirth(LocalDate.of(2000, 1, 1));

        assertEquals(LocalDate.of(2000, 1, 1), studentDTO.getDateOfBirth());
    }

    @Test
    public void dateOfBirthTestNull() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setDateOfBirth(null);

        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("dateOfBirth")));
    }

    @Test
    public void dateOfBirthTestFuture() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setDateOfBirth(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("dateOfBirth")));
    }
}
