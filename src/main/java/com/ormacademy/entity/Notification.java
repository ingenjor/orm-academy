package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity @Data
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne private User user;
}