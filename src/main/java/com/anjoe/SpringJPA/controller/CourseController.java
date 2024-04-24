package com.anjoe.SpringJPA.controller;

import com.anjoe.SpringJPA.model.Course;
import com.anjoe.SpringJPA.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping()
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(course));
    }

    @PutMapping("{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable int courseId, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, course));
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
