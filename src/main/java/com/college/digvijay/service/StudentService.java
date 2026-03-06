package com.college.digvijay.service;

import com.college.digvijay.dto.AddNewStudent;
import com.college.digvijay.dto.StudentDto;
import com.college.digvijay.entity.Student;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAll();

    Student findByIdAndStatus(Integer id);

    StudentDto saveStudent(AddNewStudent student);

    boolean deleteByIdAndStatus(Integer id);

    StudentDto updateStudent(int id,AddNewStudent student);

}
