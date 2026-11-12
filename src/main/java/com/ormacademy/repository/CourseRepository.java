package com.ormacademy.repository;
import com.ormacademy.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course, Long> {}