package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Data
public class Attachment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @ManyToOne private Submission submission;
}