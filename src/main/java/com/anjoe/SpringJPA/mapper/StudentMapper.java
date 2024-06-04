package com.anjoe.SpringJPA.mapper;

import com.anjoe.SpringJPA.dto.StudentDTO;
import com.anjoe.SpringJPA.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "teacherId", source = "classTeacher.teacherId")
    @Mapping(target = "studentId", source = "studentId")
    StudentDTO studentToStudentDTO(Student student);

    @Mapping(target = "classTeacher", ignore = true)
    @Mapping(target = "studentId", source = "studentId")
    Student studentDTOToStudent(StudentDTO studentDTO);
}
