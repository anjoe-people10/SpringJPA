package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.config.JacksonConfig;
import com.anjoe.SpringJPA.dto.StudentDTO;
import com.anjoe.SpringJPA.exception.RecordAlreadyExistException;
import com.anjoe.SpringJPA.exception.RecordNotFoundException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    private StudentController studentController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private StudentDTO studentDTO;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentDTO = new StudentDTO();
        studentDTO.setStudentId(0);
        studentDTO.setName("TestName");
        studentDTO.setDateOfBirth(LocalDate.now().minusDays(1));
        studentDTO.setTeacherId(1);
        studentController = new StudentController(studentService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new JacksonConfig().objectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStudents() {
        List<StudentDTO> students = Arrays.asList(studentDTO, studentDTO);
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<StudentDTO>> responseEntity = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(students, responseEntity.getBody());
    }

    @Test
    void getStudentById_Success() {
        int uid = 1;
        when(studentService.getStudentById(uid)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> responseEntity = studentController.getStudentById(uid);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentDTO, responseEntity.getBody());
    }

    @Test
    void createStudent_Success() {
        when(studentService.createStudent(studentDTO)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> responseEntity = studentController.createStudent(studentDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(studentDTO, responseEntity.getBody());
    }

    @Test
    void updateStudent_Success() {
        int uid = 1;
        when(studentService.updateStudent(uid, studentDTO)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> responseEntity = studentController.updateStudent(uid, studentDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentDTO, responseEntity.getBody());
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
        when(studentService.createStudent(studentDTO)).thenThrow(RecordAlreadyExistException.class);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void updateStudent_NotFound() throws Exception {
        int uid = 1;
        when(studentService.updateStudent(uid, studentDTO)).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(put("/students/{studentId}", uid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
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