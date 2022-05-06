package com.embedica.cardirectory.service;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.CarDto;
import com.embedica.cardirectory.model.CarResponse;
import com.embedica.cardirectory.model.Statistic;
import com.embedica.cardirectory.repository.CarRepositoryImpl;
import com.embedica.cardirectory.repository.ColorRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private static final String ACTION_1 = "Is empty!";

    private final CarRepositoryImpl carRepository;
    private final ColorRepositoryImpl colorRepository;

    /**
     * Find a color match in a database of available colors
     *
     * @param color match
     * @return true if exist or false
     */
    public boolean matches(String color) {
        return colorRepository.existsColorByColoring(color);
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
     * The find by id Car object
     *
     * @return Optional<Car>
     */
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    /**
     * Save object Car in to DB if him not exist
     * or update if exist
     *
     * @param car Object
     * @return car object
     */
    public Car save(Car car) {
        var rslColor = colorRepository.findColorByColoring(car.getColor().getColoring());
        if (rslColor.isPresent()) {
            car.setColor(rslColor.get());
            return carRepository.save(car);
        }
        car.setColor(colorRepository.save(car.getColor()));
        return carRepository.save(car);
    }

    /**
     * Find date when was created last entity
     * дата добавления последней записи
     * в порядке возрастания
     *
     * @return Calendar
     */
    public Calendar dateOfLastEntry() {
        var car = carRepository.findFirstByOrderByCalendarDesc();
        return car.map(Car::getCalendar).orElse(null);
    }

    /**
     * Find Id Last Entry Entity
     *
     * @return Long
     */
    public Long findIdLastEntity() {
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
     * @return boolean if present or false if Car object not exist
     */
    public boolean deleteById(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * The method returns the total number of entities saved so far
     *
     * @return Statistic obj
     */
    public Statistic statistic() {
        var rsl = carRepository.count();
        if (rsl == 0) {
            return Statistic.of(
                    ACTION_1,
                    ACTION_1,
                    ACTION_1,
                    ACTION_1
            );
        }
        return Statistic.of("The total number of entries is: " + rsl,
                "The date Of First Entry: " + this.dateOfFirstEntry().getTime(),
                "The date Of Last Entry: " + this.dateOfLastEntry().getTime(),
                "The Id Of Last Entity: " + this.findIdLastEntity());
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
     * @param year  Car obj
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

    /**
     * Pagination and Sorting Example
     *
     * @param pageNo   page number
     * @param pageSize number of entities per page
     * @param sortBy   sort in ascending or descending order
     * @param sortDir  default as ascending
     * @return CarResponse entity
     */
    @Override
    public CarResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Car> posts = carRepository.findAll(pageable);

        List<Car> listOfCars = posts.getContent();

        CarResponse carResponse = new CarResponse();
        carResponse.setContent(listOfCars);
        carResponse.setPageNo(posts.getNumber());
        carResponse.setPageSize(posts.getSize());
        carResponse.setTotalElements(posts.getTotalElements());
        carResponse.setTotalPages(posts.getTotalPages());
        carResponse.setLast(posts.isLast());
        return carResponse;
    }

    /**
     * Delete all records that belong to that repository.
     * The deleteAll() internally uses findAll() and delete() method
     */
    public void deleteAll() {
        carRepository.deleteAll();
    }
}
