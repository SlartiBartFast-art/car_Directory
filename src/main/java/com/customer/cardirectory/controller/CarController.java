package com.customer.cardirectory.controller;

import com.customer.cardirectory.model.CarResponse;
import com.customer.cardirectory.model.Statistic;
import com.customer.cardirectory.service.CarServiceImpl;
import com.customer.cardirectory.utils.AppConstants;
import com.customer.cardirectory.model.Car;
import com.customer.cardirectory.model.CarDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Validated
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarServiceImpl carService;
    private final ModelMapper modelMapper;

    /**
     * The getting a list of all records
     *
     * @return List<Car>
     */
    @GetMapping("/")
    public List<Car> findAll() {
        return carService.findAllByOrder();
    }

    /**
     * Pagination and Sorting Example
     * @param pageNo page number
     * @param pageSize number of entities per page
     * @param sortBy sort in ascending or descending order
     * @param sortDir default as ascending
     * @return CarResponse entity
     */
    @GetMapping("/cars")
    public CarResponse getAllPosts(
            @RequestParam(value = "pageNo",
                    defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize",
                    defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",
                    defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir",
                    defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return carService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    /**
     * The find by id Car object
     *
     * @return ResponseEntity<Car> Car obj or null
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return carService.findById(id)
                .map(car -> new ResponseEntity<>(car, OK))
                .orElseGet(() -> new ResponseEntity<>(new Car(), NOT_FOUND));
    }

    /**
     * The date last write Car object
     *
     * @return calendar date
     */
    @GetMapping("/lastDate")
    public ResponseEntity<Calendar> lastCarDate() {
        var rsl = Optional.of(carService.dateOfLastEntry());
        return rsl.map(calendar -> new ResponseEntity<>(calendar, OK))
                .orElseGet(() -> new ResponseEntity<>(Calendar.getInstance(), NOT_FOUND));
    }

    /**
     * The date first write Car object
     *
     * @return ResponseEntity<Calendar> calendar date or current time
     */
    @GetMapping("/firstDate")
    public ResponseEntity<Calendar> firstCarDate() {
        var rsl = Optional.of(carService.dateOfFirstEntry());
        return rsl.map(calendar -> new ResponseEntity<>(calendar, OK))
                .orElseGet(() -> new ResponseEntity<>(Calendar.getInstance(), NOT_FOUND));
    }

    /**
     * Getting a list of entities from the database,
     * by the specified parameter (color)
     *
     * @param color object
     * @return List<Car>
     */
    @GetMapping("/findByColor/{color}")
    public List<Car> findAllByColor(@PathVariable("color") String color) {
        if (!carService.matches(color)) {
            throw new IllegalArgumentException("The color object must be correct!");
        }
        return this.carService.findUsingColor(color);
    }

    /**
     * Find Car object by year and color
     *
     * @param year  Car object
     * @param color Car object
     * @return List<Car>
     */
    @GetMapping("/findByYearAndColor")
    public List<Car> findAllByColor(@Valid @RequestParam int year,
                                    @Valid @RequestParam String color) {
        return this.carService.findUsingYearAnfColor(year, color);
    }

    /**
     * Find Car object by moreThan year
     *
     * @param year Car Object
     * @return List<Car>
     */
    @GetMapping("/findByMoreThanYear/{year}")
    public List<Car> findAllByYear(@PathVariable("year") @Min(1890) @Max(2022) int year) {
        return this.carService.findMoreThanYear(year);
    }

    /**
     * Return ResultSet order by year all notes in DB
     *
     * @return List<Car>
     */
    @GetMapping("/orderByYear")
    public List<Car> orderAllByYear() {
        return this.carService.orderByYear();
    }

    /**
     * Database statistics
     *
     * @return Statistic object
     */
    @GetMapping("/statistics")
    public ResponseEntity<Statistic> statistics() {
        return new ResponseEntity<>(carService.statistic(), OK);
    }

    /**
     * ???????????????????? ????????????????????
     * ?????????????????? ???????????????? (??????????, ????????????)
     *
     * @param car Object Car
     * @return ResponseEntity<Car>
     */
    @PostMapping("/")
    public ResponseEntity<Car> save(@Valid @RequestBody CarDto car) {
        var rsl = carService.save(modelMapper.map(car, Car.class));
        if (rsl.getId() == 0) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return new ResponseEntity<>(rsl, CREATED);
    }

    /**
     * update Car obj
     *
     * @param car Car object
     * @return ResponseEntity<Void>
     */
    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody CarDto car) {
        var rsl = carService.save(modelMapper.map(car, Car.class));
        if (rsl.getId() == 0) {
            throw new ResponseStatusException(
                    INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return ResponseEntity.ok().build();
    }

    /**
     * The remove Car object by Id
     *
     * @param id Car object
     * @return HTTP status code and if the operation was successful Automotive object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Min(1) Long id) {
        if (id > carService.findIdLastEntity()) {
            throw new IllegalArgumentException(
                    "The object id must be correct, object like this id don't exist!");
        }
        return new ResponseEntity<>(carService.deleteById(id) ? OK : NOT_FOUND);
    }

}
