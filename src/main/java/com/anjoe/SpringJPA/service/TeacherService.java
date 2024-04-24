package com.anjoe.SpringJPA.service;

import com.anjoe.SpringJPA.exception.RecordAlreadyExistException;
import com.anjoe.SpringJPA.exception.RecordNotFoundException;
import com.anjoe.SpringJPA.model.Teacher;
import com.anjoe.SpringJPA.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(int teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RecordNotFoundException("Teacher"));
    }

    public Teacher createTeacher(Teacher teacher) {
        if (teacherRepository.existsById(teacher.getTeacherId())) {
            throw new RecordAlreadyExistException("Teacher");
        } else {
            teacherRepository.save(teacher);
            return teacher;
        }
    }

    public Teacher updateTeacher(int teacherId, Teacher teacher) {
        if (teacherRepository.existsById(teacherId)) {
            teacherRepository.save(teacher);
            return teacher;
        } else {
            throw new RecordNotFoundException("Teacher");
        }
    }

    public void deleteTeacher(int teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            teacherRepository.deleteById(teacherId);
        } else {
            throw new RecordNotFoundException("Teacher");
        }
    }
}


