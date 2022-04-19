package com.embedica.car_directory.service;

import com.embedica.car_directory.model.Car;

import com.embedica.car_directory.repository.CarRepositoryImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarService {

    private final ConcurrentHashMap<String, String> colors = new ConcurrentHashMap<>();

    private final CarRepositoryImpl carRepository;

    public CarService(CarRepositoryImpl carRepository) {
        init();
        this.carRepository = carRepository;
    }

    void init() {
        colors.put("White", "White");
        colors.put("Black", "Black");
        colors.put("Red", "Red");
        colors.put("Blue", "Blue");
        colors.put("Green", "Green");
        colors.put("Yellow", "Yellow");
        colors.put("Orange", "Orange");
        colors.put("Gray", "Gray");
    }

    public String matchesColor(String string) {
        String rsl = "not registered";
        if (colors.contains(string)) {
            return colors.get(string);
        }
        return rsl;
    }

    /**
     * Find all Entity in DB Asc by order - Entity id.
     *
     * @return List<Car>
     */
    public List<Car> findAllByOrder() {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    /**
     * Save object Car in to DB if him not exist
     * or update if exist
     *
     * @param car Object
     * @return car object
     */
    public Map<Boolean, Car> save(Car car) {
        Map<Boolean, Car> carBooleanMap = new HashMap<>();
        var rsl = carRepository.findById(car.getId());
        if (rsl.isEmpty()) {
            car.setCalendar(Calendar.getInstance());
            carBooleanMap.put(true, carRepository.save(car));
            return carBooleanMap;
        }
        carBooleanMap.put(false, carRepository.save(updateCar(rsl.get(), car)));
        return carBooleanMap;
    }

    private Car updateCar(Car rsl, Car car) {
        rsl.setNumber(car.getNumber());
        rsl.setMark(car.getMark());
        rsl.setColor(car.getColor());
        rsl.setYear(car.getYear());
        return rsl;
    }

    /**
     * Find date when was created last entity
     * дата добавления последней записи
     * в порядке возрастания
     *
     * @return
     */
    public Calendar dateOfLastEntry() {
        var car = carRepository.findFirstByOrderByCalendarDesc();
        return car.map(Car::getCalendar).orElse(null);
    }

    /**
     * Find Id Last Entry Entity
     *
     * @return Integer
     */
    public Integer findIdLastEntity() {
        var car = carRepository.findFirstByOrderByCalendarDesc();
        return car.map(Car::getId).orElse(null);
    }


    /**
     * Find date when was created first entity
     *
     * @return Calendar
     */
    public Calendar dateOfFirstEntry() {
        var car = carRepository.findFirstByOrderByCalendarAsc();
        return car.map(Car::getCalendar).orElse(null);
    }

    /**
     * remove Car object
     *
     * @param id Car object
     * @return Car obj if present or empty Car object if not
     */
    public Car whenRemovedCar(int id) {
        var rsl = carRepository.findById(id);
        if (rsl.isPresent()) {
            carRepository.deleteById(id);
            return rsl.get();
        }
        return Car.of(null, null, null, 0);
    }

    /**
     * The total number of records stored in the database
     *
     * @return Long
     */
    public Long count() {
        return carRepository.count();
    }

    /**
     * The method returns the total number of entities saved so far
     *
     * @return String value or "Is empty!"
     */
    public String countStatistic() {
        Long rsl = carRepository.count();
        if (rsl == 0) {
            return "Is empty!";
        }
        return "The total number of entries is:" +
                rsl;
    }

    /**
     * Find all Car by color
     *
     * @param color Car obj
     * @return List<Car>
     */
    public List<Car> findUsingColor(String color) {
        return carRepository.findAllByColor(color);
    }

    /**
     * Find all Car by color and year
     *
     * @param year Car obj
     * @param color Car obj
     * @return List<Car>
     */
    public List<Car> findUsingYearAnfColor(int year, String color) {
        return carRepository.findAllByYearAndColor(year, color);
    }

    /**
     * Find all Car moreThen year
     *
     * @param year Car obj
     * @return List<Car>
     */
    public List<Car> findMoreThanYear(int year) {
        return carRepository.findAllByYear(year);
    }

    /**
     * The method returns the List<Car> orderByYear
     *
     * @return List<Car>
     */
    public List<Car> orderByYear() {
        return carRepository.findCarByYearOrderByYear();
    }
}
