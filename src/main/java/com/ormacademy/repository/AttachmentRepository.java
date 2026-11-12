package com.ormacademy.repository;
import com.ormacademy.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {}