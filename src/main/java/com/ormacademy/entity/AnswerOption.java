package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Data
public class AnswerOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean isCorrect;
    @ManyToOne private Question question;
}