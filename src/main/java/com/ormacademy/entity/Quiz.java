package com.ormacademy.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();
}