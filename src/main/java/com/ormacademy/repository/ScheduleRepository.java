package com.ormacademy.repository;
import com.ormacademy.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {}