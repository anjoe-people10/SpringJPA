package com.anjoe.SpringJPA.service;

import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    //Lombok internally does this
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int uid) {
        return studentRepository.findById(uid);
    }

    public boolean createStudent(Student student) {
        if (studentRepository.existsById(student.getUid())) {
            return false;
        } else {
            studentRepository.save(student);
            return true;
        }
    }

    public boolean updateStudent(int uid, Student newStudent) {
        if (studentRepository.existsById(uid)) {
            studentRepository.save(newStudent);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteStudent(int uid) {
        if (studentRepository.existsById(uid)) {
            studentRepository.deleteById(uid);
            return true;
        } else {
            return false;
        }
    }
}
