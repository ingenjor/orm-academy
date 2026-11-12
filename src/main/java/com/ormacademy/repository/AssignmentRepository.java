package com.ormacademy.repository;
import com.ormacademy.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {}