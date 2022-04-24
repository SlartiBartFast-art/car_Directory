package com.embedica.cardirectory.repository;

import com.embedica.cardirectory.model.Car;

import java.util.List;

public interface CustomCarRepository {

    Car customFindMethod(Long id);

    List<Car> findByRequestParamCustom (String id, String color, String year, String sort);
}
