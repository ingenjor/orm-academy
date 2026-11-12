package com.ormacademy.repository;
import com.ormacademy.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}