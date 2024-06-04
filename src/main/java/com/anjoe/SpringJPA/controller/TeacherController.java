package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.model.Teacher;
import com.anjoe.SpringJPA.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int teacherId) {
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @PostMapping()
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
    }

    @PutMapping("{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int teacherId, @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(teacherId, teacher));
    }

    @DeleteMapping("{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }
}
