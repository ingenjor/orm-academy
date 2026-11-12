package com.ormacademy.repository;
import com.ormacademy.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
public interface QuestionRepository extends JpaRepository<Question, Long> {}