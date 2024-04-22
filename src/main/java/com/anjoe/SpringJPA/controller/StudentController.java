package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.service.StudentService;
import com.anjoe.SpringJPA.util.GetErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    private GetErrorResponse errorResponse;

    public StudentController(@Autowired StudentService studentService) {
        this.studentService = studentService;
    }

    //Return a list of all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //Returns student with /student/<uid> with GET request
    @GetMapping("/{uid}")
    public ResponseEntity<?> getStudentById(@PathVariable int uid) {
        Optional<Student> optionalStudent = studentService.getStudentById(uid);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        } else {
            return GetErrorResponse.studentNotFound();
        }
    }


    //Creates new student with json in request body with POST request
    @PostMapping()
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        if (studentService.createStudent(student)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(student);
        } else {
            return GetErrorResponse.studentAlreadyExists();
        }
    }

    //Updates existing student with /student/<uid> and json in request body using PUT request
    @PutMapping("/{uid}")
    public ResponseEntity<?> updateStudent(@PathVariable int uid, @Valid @RequestBody Student newStudent) {
        if (studentService.updateStudent(uid, newStudent)) {
            return ResponseEntity.ok().body(newStudent);
        } else {
            return GetErrorResponse.studentNotFound();
        }
    }

    //Deleting existing student with /student/<uid> using DELETE request
    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteStudent(@PathVariable int uid) {
        if (studentService.deleteStudent(uid)) {
            return ResponseEntity.noContent().build();
        } else {
            return GetErrorResponse.studentNotFound();
        }
    }
}