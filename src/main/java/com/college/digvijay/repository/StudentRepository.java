package com.college.digvijay.repository;

import com.college.digvijay.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByStudentIdAndStatusIsTrue(Integer id);
    List<Student> findAllByStatusIsTrue();

}
