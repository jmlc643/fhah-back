package com.upao.pe.fhahback.repositories;

import com.upao.pe.fhahback.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findByColorName(String colorName);

    boolean existsByColorName(String colorName);

    List<Color> findByColorNameIn(List<String> colorName);
}
