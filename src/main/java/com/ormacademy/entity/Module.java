package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
@Entity @Data
public class Module {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL) @JsonIgnore
    private List<Lesson> lessons;
}