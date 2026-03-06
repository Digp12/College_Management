package com.college.digvijay.service;

import com.college.digvijay.dto.AddNewStudent;
import com.college.digvijay.dto.SendStudent;
import com.college.digvijay.dto.StudentDto;
import com.college.digvijay.entity.Classes;
import com.college.digvijay.entity.Division;
import com.college.digvijay.entity.Student;
import com.college.digvijay.exceptions.ClassesNotFound;
import com.college.digvijay.exceptions.DivisionNotFound;
import com.college.digvijay.exceptions.EmailExists;
import com.college.exceptions.StudentNotFound;
import com.college.digvijay.repository.ClassesRepository;
import com.college.digvijay.repository.DivisionRepository;
import com.college.digvijay.repository.StudentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private DivisionRepository divisionRepository;
    private ClassesRepository classesRepository;
    private KafkaService kafkaService;

    public StudentServiceImpl(StudentRepository studentRepository, DivisionRepository divisionRepository, ClassesRepository classesRepository, KafkaService kafkaService) {
        this.studentRepository = studentRepository;
        this.divisionRepository = divisionRepository;
        this.classesRepository = classesRepository;
        this.kafkaService = kafkaService;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAllByStatusIsTrue();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto std = new StudentDto(student.getName(),
                    student.getEmail()
                    , student.getAge()
                    , student.getClasses().getCode()
                    , student.getDivision().getDivisionCode());
            studentDtos.add(std);
        }
        return studentDtos;

    }

    @Override
    public Student findByIdAndStatus(Integer id) {
        return studentRepository.findByStudentIdAndStatusIsTrue(id).orElseThrow(() -> new StudentNotFound("student not found" + id));
    }

    @Override
    public StudentDto saveStudent(AddNewStudent student) {

        Student std = new Student();
        std.setName(student.getName());
        std.setAge(student.getAge());
        std.setEmail(student.getEmail());

        Classes cs = classesRepository
                .findById(student.getClassesId())
                .orElseThrow(() -> new ClassesNotFound("class not found " + student.getClassesId()));
        std.setClasses(cs);

        Division div = divisionRepository
                .findById(student.getDivisionId())
                .orElseThrow(() -> new DivisionNotFound("division not found " + student.getDivisionId()));
        std.setDivision(div);
        std.setStatus(true);

        try {
            Student student1 = studentRepository.save(std);
            SendStudent student2 = new SendStudent(
                    student1.getStudentId(),
                    student1.getName(),
                    student1.getEmail(),
                    student1.getAge(),
                    student1.getClasses().getClassesId(),
                    student1.getDivision().getDivisionId()
            );

            kafkaService.sendStudent(student2);


            return new StudentDto(
                    student1.getName(),
                    student1.getEmail(),
                    student1.getAge(),
                    student1.getClasses().getCode(),
                    student1.getDivision().getDivisionCode()
            );

        } catch (DataIntegrityViolationException e) {
            throw new EmailExists("student email already exists " + student.getEmail());
        }
    }

    @Override
    public boolean deleteByIdAndStatus(Integer id) {
        if (studentRepository.existsById(id)) {
            Student student = this.findByIdAndStatus(id);
            student.setStatus(false);
            studentRepository.save(student);
            return true;
        } else {
            throw new StudentNotFound("student not found " + id);
        }
    }

    @Override
    public StudentDto updateStudent(int id, AddNewStudent newstudent) {

        Student oldstudent = this.findByIdAndStatus(id);


        Classes cs = classesRepository
                .findById(newstudent.getClassesId())
                .orElseThrow(() -> new ClassesNotFound("class not found " + newstudent.getClassesId()));

        Division div = divisionRepository
                .findById(newstudent.getDivisionId())
                .orElseThrow(() -> new DivisionNotFound("division not found " + newstudent.getDivisionId()));

        oldstudent.setName(newstudent.getName());
        oldstudent.setEmail(newstudent.getEmail());
        oldstudent.setAge(newstudent.getAge());
        oldstudent.setClasses(cs);
        oldstudent.setDivision(div);


        Student std = studentRepository.save(oldstudent);

        return new StudentDto(
                std.getName(),
                std.getEmail(),
                std.getAge(),
                std.getClasses().getCode(),
                std.getDivision().getDivisionCode()
        );

    }
}
