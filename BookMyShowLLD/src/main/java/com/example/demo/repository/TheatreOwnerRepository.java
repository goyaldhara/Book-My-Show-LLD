package com.example.demo.repository;

import com.example.demo.model.entity.TheatreOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheatreOwnerRepository extends JpaRepository<TheatreOwner,Long> {
    Optional<TheatreOwner>findById(long id);
}
