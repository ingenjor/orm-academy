package com.ormacademy.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity @Data
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Инициализируем список, чтобы не было null
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Module> modules = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "course_tags", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonIgnore
    private Set<Tag> tags;
}