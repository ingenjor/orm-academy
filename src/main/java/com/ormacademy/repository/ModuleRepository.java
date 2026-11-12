package com.ormacademy.repository;
import com.ormacademy.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ModuleRepository extends JpaRepository<Module, Long> {}