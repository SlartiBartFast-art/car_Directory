package com.embedica.car_directory.repository;

import com.embedica.car_directory.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepositoryImpl extends JpaRepository<Color, Long> {

    boolean existsColorByColoring(String coloring);
}
