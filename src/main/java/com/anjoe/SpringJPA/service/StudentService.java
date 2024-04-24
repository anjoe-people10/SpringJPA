package com.anjoe.SpringJPA.service;

import com.anjoe.SpringJPA.exception.RecordAlreadyExistException;
import com.anjoe.SpringJPA.exception.RecordNotFoundException;
import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    //Lombok internally does this
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Student"));
    }

    public Student createStudent(Student student) {
        if (studentRepository.existsById(student.getStudentId())) {
            throw new RecordAlreadyExistException("Student");
        } else {
            studentRepository.save(student);
            return student;
        }
    }

    public Student updateStudent(int studentId, Student student) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.save(student);
            return student;
        } else {
            throw new RecordNotFoundException("Student");
        }
    }

    public void deleteStudent(int studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        } else {
            throw new RecordNotFoundException("Student");
        }
    }
}
