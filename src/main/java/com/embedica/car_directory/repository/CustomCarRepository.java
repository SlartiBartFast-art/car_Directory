package com.embedica.car_directory.repository;

import com.embedica.car_directory.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomCarRepository {

    Car customFindMethod(Long id);

    List<Car> findByRequestParamCustom (String id, String color, String year, String sort);
}
