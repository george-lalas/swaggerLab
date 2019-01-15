package com.example.swaggerlab.repository;


import com.example.swaggerlab.model.PlanetaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetaryDataRepository extends JpaRepository<PlanetaryData,Long> {
}
