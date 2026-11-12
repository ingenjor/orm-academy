package com.ormacademy.repository;
import com.ormacademy.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProfileRepository extends JpaRepository<Profile, Long> {}