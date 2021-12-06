package com.example.demo.repository;

import com.example.demo.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);
    Optional<City> findByCityName(String name);
    List<City>findAll();
}
