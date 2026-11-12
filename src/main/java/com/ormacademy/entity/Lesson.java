package com.ormacademy.entity;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
@Entity @Data
public class Lesson {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne @JoinColumn(name = "module_id")
    private Module module;
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL) @JsonIgnore
    private List<Assignment> assignments;
}