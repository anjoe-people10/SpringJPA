package com.anjoe.SpringJPA.model;

import com.anjoe.SpringJPA.model.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void nameTestValid() {
        Student student = new Student();
        student.setName("John Doe");
        assertEquals(student.getName(), "John Doe");
    }

    @Test
    public void nameTestNull() {
        Student student = new Student();
        student.setName(null);
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void nameTestEmpty() {
        Student student = new Student();
        student.setName("");
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void nameTestBlank() {
        Student student = new Student();
        student.setName("   ");

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("name")));
    }

    @Test
    public void dateOfBirthTestValid() {
        Student student = new Student();
        student.setDateOfBirth(LocalDate.of(2000, 1, 1));

        assertEquals(LocalDate.of(2000, 1, 1), student.getDateOfBirth());
    }

    @Test
    public void dateOfBirthTestNull() {
        Student student = new Student();
        student.setDateOfBirth(null);

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("dateOfBirth")));
    }

    @Test
    public void dateOfBirthTestFuture() {
        Student student = new Student();
        student.setDateOfBirth(LocalDate.now().plusDays(1));

        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        assertTrue(violations.stream().anyMatch(violation -> violation.getPropertyPath().toString().equals("dateOfBirth")));
    }
}
