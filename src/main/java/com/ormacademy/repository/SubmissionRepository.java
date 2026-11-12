package com.ormacademy.repository;
import com.ormacademy.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SubmissionRepository extends JpaRepository<Submission, Long> {}