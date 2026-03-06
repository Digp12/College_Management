package com.college.digvijay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewStudent {
    private String name;
    private String email;
    private int age;
    private int classesId;
    private int divisionId;
}
