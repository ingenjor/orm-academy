package com.ormacademy.repository;
import com.ormacademy.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {}