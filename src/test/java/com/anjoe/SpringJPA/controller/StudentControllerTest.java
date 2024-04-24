package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.config.JacksonConfig;
import com.anjoe.SpringJPA.exception.RecordAlreadyExistException;
import com.anjoe.SpringJPA.exception.RecordNotFoundException;
import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.model.Teacher;
import com.anjoe.SpringJPA.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private StudentController studentController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private Student student;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        student = new Student(1, "TesterName", LocalDate.now().minusDays(1), new Teacher());
        studentController = new StudentController(studentService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new JacksonConfig().objectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStudents() {
        List<Student> students = Arrays.asList(student, student);
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<Student>> responseEntity = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(students, responseEntity.getBody());
    }

    @Test
    void getStudentById_Success() {
        int uid = 1;
        when(studentService.getStudentById(uid)).thenReturn(student);

        ResponseEntity<Student> responseEntity = studentController.getStudentById(uid);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void createStudent_Success() {
        when(studentService.createStudent(student)).thenReturn(student);

        ResponseEntity<Student> responseEntity = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void updateStudent_Success() {
        int uid = 1;
        when(studentService.updateStudent(uid, student)).thenReturn(student);

        ResponseEntity<Student> responseEntity = studentController.updateStudent(uid, student);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(student, responseEntity.getBody());
    }

    @Test
    void deleteStudent_Success() {
        int uid = 1;
        doNothing().when(studentService).deleteStudent(uid);

        ResponseEntity<Void> responseEntity = studentController.deleteStudent(uid);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void getStudentById_NotFound() throws Exception {
        int uid = 1;
        when(studentService.getStudentById(uid)).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(get("/students/{studentId}", uid))
                .andExpect(status().isBadRequest());
    }


    @Test
    void createStudent_AlreadyExists() throws Exception {
        when(studentService.createStudent(student)).thenThrow(RecordAlreadyExistException.class);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isConflict());
    }

    @Test
    void updateStudent_NotFound() throws Exception{
        int uid = 1;
        when(studentService.updateStudent(uid, student)).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(put("/students/{studentId}", uid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void deleteStudent_NotFound() throws Exception {
        int uid = 1;
        doThrow(RecordNotFoundException.class).when(studentService).deleteStudent(uid);

        mockMvc.perform(delete("/students/{studentId}", uid))
                .andExpect(status().isBadRequest());
    }
}