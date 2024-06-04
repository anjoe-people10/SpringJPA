package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.dto.StudentDTO;
import com.anjoe.SpringJPA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    //Return a list of all students
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //Returns student with /student/<studentId> with GET request
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    //Creates new student with json in request body with POST request
    @PostMapping()
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDTO));
    }

    //Updates existing student with /student/<studentId> and json in request body using PUT request
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable int studentId, @Valid @RequestBody StudentDTO newStudentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, newStudentDTO));
    }

    //Deleting existing student with /student/<studentId> using DELETE request
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}