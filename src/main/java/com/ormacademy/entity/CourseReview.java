package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
@Entity @Data
public class CourseReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private Course course;
    @ManyToOne private User student;
    private Integer rating;
    private String comment;
    private Instant createdAt;
}