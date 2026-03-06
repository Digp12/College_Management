package com.college.digvijay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String name;

    @Column(unique = true)
    private String email;
    private int age;
    @ManyToOne
    @JoinColumn(name = "classes_id")
    private Classes classes;

    @ManyToOne
    @JoinColumn(name="division_id")
    private Division division;

    private boolean status;

}
