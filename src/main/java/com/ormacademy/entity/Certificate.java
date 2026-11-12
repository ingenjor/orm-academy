package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Data
public class Certificate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serial;
    @OneToOne private Enrollment enrollment;
}