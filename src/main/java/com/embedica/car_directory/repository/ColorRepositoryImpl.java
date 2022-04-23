package com.embedica.car_directory.repository;

import com.embedica.car_directory.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepositoryImpl extends JpaRepository<Color, Long> {

    boolean existsColorByColoring(String coloring);

    Optional<Color> findColorByColoring(String coloring);

}
