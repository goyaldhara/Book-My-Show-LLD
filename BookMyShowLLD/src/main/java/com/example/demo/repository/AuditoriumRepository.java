package com.example.demo.repository;

import com.example.demo.model.entity.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium, Long> {
    Optional<Auditorium>findById(Long id);
}
