package com.springfield.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springfield.springboot.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s.password from Student s where s.username = ?1")
    String lookupStudentPassword(String username);
}
