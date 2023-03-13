package com.student.assignment.Student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer>{

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:keyword% OR s.department LIKE %:keyword% OR s.email LIKE %:keyword%")
    List<Student> searchByKeyword(String keyword);
    
}
