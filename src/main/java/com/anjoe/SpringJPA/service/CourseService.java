package com.anjoe.SpringJPA.service;

import com.anjoe.SpringJPA.model.Course;
import com.anjoe.SpringJPA.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
