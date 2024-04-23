package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.service.StudentService;
import com.anjoe.SpringJPA.util.GetErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private StudentController studentController;
    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentController = new StudentController(studentService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStudents() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<Student>> responseEntity = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(students, responseEntity.getBody());
    }

    @Test
    void getStudentById_Found() {
        int uid = 1;
        Student student = new Student();
        when(studentService.getStudentById(uid)).thenReturn(Optional.of(student));

        ResponseEntity<?> responseEntity = studentController.getStudentById(uid);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void getStudentById_NotFound() {
        int uid = 1;
        when(studentService.getStudentById(uid)).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = studentController.getStudentById(uid);

        assertEquals(GetErrorResponse.studentNotFound(), responseEntity);
    }

    @Test
    void createStudent_Success() {
        Student student = new Student();
        when(studentService.createStudent(student)).thenReturn(true);

        ResponseEntity<?> responseEntity = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void createStudent_AlreadyExists() {
        Student student = new Student();
        when(studentService.createStudent(student)).thenReturn(false);

        ResponseEntity<?> responseEntity = studentController.createStudent(student);

        assertEquals(GetErrorResponse.studentAlreadyExists(), responseEntity);
    }

    @Test
    void updateStudent_Success() {
        int uid = 1;
        Student student = new Student();
        when(studentService.updateStudent(uid, student)).thenReturn(true);

        ResponseEntity<?> responseEntity = studentController.updateStudent(uid, student);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void updateStudent_NotFound() {
        int uid = 1;
        Student student = new Student();
        when(studentService.updateStudent(uid, student)).thenReturn(false);

        ResponseEntity<?> responseEntity = studentController.updateStudent(uid, student);

        assertEquals(GetErrorResponse.studentNotFound(), responseEntity);
    }

    @Test
    void deleteStudent_Found() {
        int uid = 1;
        Student student = new Student();
        when(studentService.deleteStudent(uid)).thenReturn(true);

        ResponseEntity<?> responseEntity = studentController.deleteStudent(uid);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void deleteStudent_NotFound() {
        int uid = 1;
        Student student = new Student();
        when(studentService.deleteStudent(uid)).thenReturn(false);

        ResponseEntity<?> responseEntity = studentController.deleteStudent(uid);

        assertEquals(GetErrorResponse.studentNotFound(), responseEntity);
    }
}