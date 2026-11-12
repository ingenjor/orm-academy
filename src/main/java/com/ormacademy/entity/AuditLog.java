package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
@Entity @Data
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String action;
    private Instant time;
}