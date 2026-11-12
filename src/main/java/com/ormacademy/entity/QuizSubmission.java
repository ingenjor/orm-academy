package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
@Entity @Data
public class QuizSubmission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private User student;
    @ManyToOne private Quiz quiz;
    private Integer score;
    private Instant takenAt;
}