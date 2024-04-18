package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Return a list of all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    //Returns student with /student/<uid>
    @GetMapping("/{uid}")
    public ResponseEntity<Student> getStudentById(@PathVariable int uid) {
        Optional<Student> optionalStudent = studentService.getStudentById(uid);
        return optionalStudent.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    //Creates new student with json in request body with POST request
    @PostMapping()
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        if (studentService.createStudent(student)) {
            return new ResponseEntity<>("Successful", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student already exists", HttpStatus.CONFLICT);
        }
    }

    //Updates existing student with /student/<uid> and json in request body using PUT request
    @PutMapping("/{uid}")
    public ResponseEntity<String> updateStudent(@PathVariable int uid, @RequestBody Student newStudent) {
        if (studentService.updateStudent(uid, newStudent)) {
            return new ResponseEntity<>("Successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student does not exist", HttpStatus.NOT_FOUND);
        }
    }

    //Deleting existing student with /student/<uid> using DELETE request
    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteStudent(@PathVariable int uid) {
        if (studentService.deleteStudent(uid)) {
            return new ResponseEntity<>("Successful", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Student does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
