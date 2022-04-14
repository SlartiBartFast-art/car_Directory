package com.embedica.car_directory.service;

import com.embedica.car_directory.model.Car;
import com.embedica.car_directory.repository.CarRepository;

import org.springframework.stereotype.Service;

import java.text.ParseException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CarService {

    private final ConcurrentHashMap<String, String> colors = new ConcurrentHashMap<>();

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
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
     * Find all Entity in DB
     * @return List<Car>
     */
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    /**
     * Save object Car in to DB if him not exist
     * @param car
     * @return
     */
    public Map<Boolean, Car> save(Car car) {
        Map<Boolean, Car> carBooleanMap = new HashMap<>();
        Optional<Car> rsl = carRepository.findCarByNumberAndMarkAndColorAndYear(
                car.getNumber(),
                car.getMark(),
                car.getColor(),
                car.getYear()
        );
        if (rsl.isEmpty()) {
            car.setCalendar(Calendar.getInstance());
            carBooleanMap.put(true,carRepository.save(car));
            return carBooleanMap;
        }
        carBooleanMap.put(false, car);
        return carBooleanMap;
    }

    /**
     * дата добавления последней записи
     * @return
     */
    public Calendar dateOfLastEntry() {

        var count = carRepository.count();
        var car = carRepository.findById(Math.toIntExact(count));
        if (car.isPresent()) {
            return car.get().getCalendar();
        }
        return null;
    }

    /**
     * Find date when was created first entity
     * @return Calendar
     * @throws ParseException
     */
    public Calendar dateOfFirstEntry() {
        var count = carRepository.count();
        if (count != 0) {
            var car = carRepository.findById(1);
            if (car.isPresent()) {
                return car.get().getCalendar();
            }
        }
        return null;
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
     * @return
     */
    public String countStatistic() {
        Long rsl = carRepository.count();
        if (rsl == 0) {
            return "Is empty!";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("The total number of entries is:");
        stringBuilder.append(rsl);
        return stringBuilder.toString();
    }

    /**
     * Find all Car by color
     * @param color
     * @return List<Car>
     */
    public List<Car> findUsingColor(String color) {
        return carRepository.findAllByColor(color);
    }

    /**
     * Find all Car by color and year
     * @param year
     * @param color
     * @return List<Car>
     */
    public List<Car> findUsingYearAnfColor(int year, String color) {
        return carRepository.findAllByYearAndColor(year, color);
    }

    /**
     * Find all Car moreThen year
     * @param year
     * @return List<Car>
     */
    public List<Car> findMoreThanYear(int year) {
        return carRepository.findAllByYear(year);
    }

    /**
     * The method returns the List<Car> orderByYear
     * @return List<Car>
     */
    public List<Car> orderByYear() {
        return carRepository.findCarByYearOrderByYear();
    }
}
