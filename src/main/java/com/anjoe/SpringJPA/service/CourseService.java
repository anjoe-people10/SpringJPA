package com.anjoe.SpringJPA.service;

import com.anjoe.SpringJPA.exception.RecordAlreadyExistException;
import com.anjoe.SpringJPA.exception.RecordNotFoundException;
import com.anjoe.SpringJPA.exception.PrimaryKeyUpdateException;
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

    public Course getCourseById(int courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RecordNotFoundException("Course"));
    }

    public Course createCourse(Course course) {
        if (courseRepository.existsById(course.getCourseId())) {
            throw new RecordAlreadyExistException("Course");
        } else {
            return courseRepository.save(course);
        }
    }

    public Course updateCourse(int courseId, Course course) {
        if (courseRepository.existsById(courseId)) {
            if (courseId == course.getCourseId()) {
                courseRepository.save(course);
                return course;
            } else {
                throw new PrimaryKeyUpdateException();
            }
        } else {
            throw new RecordNotFoundException("Course");
        }
    }

    public void deleteCourse(int courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
        } else {
            throw new RecordNotFoundException("Course");
        }
    }
}
