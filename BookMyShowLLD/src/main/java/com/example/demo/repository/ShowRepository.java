package com.example.demo.repository;

import com.example.demo.model.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    Optional<Show>findById(long showId);
}
