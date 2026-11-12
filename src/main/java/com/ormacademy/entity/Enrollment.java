package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity @Data
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private User student;
    @ManyToOne private Course course;
    private LocalDate enrollDate;
    private String status;
}