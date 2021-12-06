package com.example.demo.repository;

import com.example.demo.model.entity.Theatre;
import com.example.demo.model.entity.TheatreOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long> {
    Optional<Theatre> findById(long id);

}
