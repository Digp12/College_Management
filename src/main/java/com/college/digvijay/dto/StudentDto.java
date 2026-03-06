package com.college.digvijay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {
    private String name;
    private String email;
    private int age;
    private String classesCode;
    private String divisionCode;
}
