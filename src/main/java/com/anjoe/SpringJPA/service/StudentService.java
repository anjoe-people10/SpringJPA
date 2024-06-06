package com.anjoe.SpringJPA.service;

import com.anjoe.SpringJPA.dto.StudentDTO;
import com.anjoe.SpringJPA.exception.RecordAlreadyExistException;
import com.anjoe.SpringJPA.exception.RecordNotFoundException;
import com.anjoe.SpringJPA.mapper.StudentMapper;
import com.anjoe.SpringJPA.model.Student;
import com.anjoe.SpringJPA.repository.StudentRepository;
import com.anjoe.SpringJPA.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper.INSTANCE::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(int studentId) {
        return StudentMapper.INSTANCE.studentToStudentDTO(
                studentRepository.findById(studentId)
                        .orElseThrow(() -> new RecordNotFoundException("Student"))
        );
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.INSTANCE.studentDTOToStudent(studentDTO);
        if (studentRepository.existsById(student.getStudentId())) {
            throw new RecordAlreadyExistException("Student");
        } else {
            student.setClassTeacher(teacherRepository.findById(studentDTO.getTeacherId())
                    .orElseThrow(() -> new RecordNotFoundException("Teacher")));
            studentRepository.save(student);
            return StudentMapper.INSTANCE.studentToStudentDTO(student);
        }
    }

    public StudentDTO updateStudent(int studentId, StudentDTO studentDTO) {
        Student student = StudentMapper.INSTANCE.studentDTOToStudent(studentDTO);
        if (studentRepository.existsById(studentId)) {
            student.setClassTeacher(teacherRepository.findById(studentDTO.getTeacherId())
                    .orElseThrow(() -> new RecordNotFoundException("Teacher")));
            studentRepository.save(student);
            return StudentMapper.INSTANCE.studentToStudentDTO(student);
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
