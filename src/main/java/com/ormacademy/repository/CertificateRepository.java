package com.ormacademy.repository;
import com.ormacademy.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CertificateRepository extends JpaRepository<Certificate, Long> {}