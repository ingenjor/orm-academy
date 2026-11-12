package com.ormacademy.repository;
import com.ormacademy.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificationRepository extends JpaRepository<Notification, Long> {}