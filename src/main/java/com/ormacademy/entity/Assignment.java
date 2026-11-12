package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity @Data
public class Assignment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Integer maxScore;
    @ManyToOne @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}