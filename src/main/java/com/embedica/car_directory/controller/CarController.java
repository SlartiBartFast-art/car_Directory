package com.embedica.car_directory.controller;

import com.embedica.car_directory.model.Car;

import com.embedica.car_directory.model.CarDto;
import com.embedica.car_directory.service.CarService;

import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.Calendar;

import java.util.List;

@Validated
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    private final ModelMapper modelMapper;

    public CarController(CarService carService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    /**
     * The getting a list of all records
     *
     * @return List<Car>
     */
    @GetMapping("/all")
    public List<Car> findAll() {
        return carService.findAllByOrder();
    }

    /**
     * Добавление автомобиля
     * Результат операции (успех, ошибка, объект уже существует)
     *
     * @param car Object Car
     * @return ResponseEntity<Car>
     */
    @PostMapping("/income")
    public ResponseEntity<Car> whenAddNewCar(@Valid @RequestBody CarDto car) {
        var rsl = carService.save(modelMapper.map(car, Car.class));
        if (rsl.containsKey(false)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The object is already exist!!!."
            );
        }
        return new ResponseEntity<>(
                rsl.get(true),
                HttpStatus.OK
        );
    }

    /**
     * The date last write Car object
     *
     * @return calendar date
     */
    @GetMapping("/lastDate")
    public ResponseEntity<Calendar> whenLastDateCar() {
        Calendar rsl = carService.dateOfLastEntry();
        return new ResponseEntity<>(rsl,
                rsl != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * The date first write Car object
     *
     * @return calendar date
     */
    @GetMapping("/firstDate")
    public ResponseEntity<Calendar> whenFirstDateCar() {
        var rsl = carService.dateOfFirstEntry();
        return new ResponseEntity<>(rsl,
                rsl != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

    /**
     * The remove Car object by Id
     *
     * @param id Car object
     * @return HTTP status code and if the operation was successful Automotive object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> removeCar(@PathVariable("id") @Min(1) int id) {
        var count = carService.findIdLastEntity();
        if (id > count) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The object id must be correct, object like this id don't exist!");
        }
        var rsl = carService.whenRemovedCar(id);
        return new ResponseEntity<>(rsl,
                rsl.getColor() != null && rsl.getMark() != null ?
                        HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    /**
     * Database statistics - number of records
     *
     * @return String object
     */
    @GetMapping("/stcCount")
    public ResponseEntity<String> getAllCountStatistics() {
        return new ResponseEntity<>(carService.countStatistic(),
                HttpStatus.OK);
    }


    /**
     * Getting a list of entities from the database,
     * by the specified parameter (color)
     *
     * @param color object
     * @return List<Car>
     */
    @GetMapping("/fndByClr/{color}")
    public List<Car> findAllByColor(@PathVariable("color") String color) {
        if (carService.matchesColor(color).equals("not registered")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The color object must be correct.");
        }
        return this.carService.findUsingColor(color).stream().toList();
    }

    /**
     * Find Car object by year and color
     *
     * @param year Car object
     * @param color Car object
     * @return List<Car>
     */
    @GetMapping("/fndByClrAndYear")
    public List<Car> findAllByColor(@Valid @RequestParam int year,
                                    @Valid @RequestParam String color) {
        return this.carService.findUsingYearAnfColor(year, color).stream().toList();
    }

    /**
     * Find Car object by moreThan year
     *
     * @param year Car Object
     * @return List<Car>
     */
    @GetMapping("/fndByMTYear/{year}")
    public List<Car> findAllByYear(@PathVariable("year") @Min(1890) @Max(2022) int year) {
        return this.carService.findMoreThanYear(year).stream().toList();
    }

    /**
     * Return ResultSet order by year all notes in DB
     *
     * @return List<Car>
     */
    @GetMapping("/ordByYear")
    public List<Car> orderAllByYear() {
        return this.carService.orderByYear().stream().toList();
    }
}
