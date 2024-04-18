package com.anjoe.SpringJPA.repository;

import com.anjoe.SpringJPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Where <Student, Integer> is <Record type, Primary Key type>
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
