package com.college.digvijay.controller;

import com.college.digvijay.dto.AddNewStudent;
import com.college.digvijay.dto.StudentDto;
import com.college.digvijay.entity.Student;
import com.college.digvijay.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students=studentService.findAll();
        return ResponseEntity.ok().body(students);
    }
    @PostMapping("/save")
    public ResponseEntity<StudentDto> createStudent(@RequestBody AddNewStudent student) {
       return ResponseEntity.ok().body(studentService.saveStudent(student));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable int id,@RequestBody AddNewStudent student) {
        StudentDto updated=studentService.updateStudent(id,student);
        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
       if( studentService.deleteByIdAndStatus(id)){
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("Student not deleted", HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student=studentService.findByIdAndStatus(id);
        return ResponseEntity.ok().body(student);
    }

}
