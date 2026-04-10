package com.studentmgmt.repository;

import com.studentmgmt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByEmail(String email);

    List<Student> findByStatus(String status);

    List<Student> findByCourseContainingIgnoreCase(String course);

    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(s.lastName)  LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(s.email)     LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(s.course)    LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(s.id)        LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Student> search(@Param("q") String query);

    @Query("SELECT MAX(CAST(SUBSTRING(s.id, 5) AS int)) FROM Student s")
    Integer findMaxSequence();
}
