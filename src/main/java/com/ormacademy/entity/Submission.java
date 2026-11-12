package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
@Entity @Data
public class Submission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private User student;
    @ManyToOne private Assignment assignment;
    private String content;
    private Integer score;
    private Instant submittedAt;
}