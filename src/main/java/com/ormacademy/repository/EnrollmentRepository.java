package com.ormacademy.repository;
import com.ormacademy.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {}