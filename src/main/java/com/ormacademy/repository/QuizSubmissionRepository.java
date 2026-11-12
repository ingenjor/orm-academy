package com.ormacademy.repository;
import com.ormacademy.entity.QuizSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {}