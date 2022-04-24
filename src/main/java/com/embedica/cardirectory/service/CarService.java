package com.embedica.cardirectory.service;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.Statistic;
import com.embedica.cardirectory.repository.CarRepositoryImpl;
import com.embedica.cardirectory.repository.ColorRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CarService {

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

    //todo
    public boolean contains(Long id) {
        return carRepository.existsById(id);
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
        var rslOptional = carRepository.findById(id);
        if (rslOptional.isPresent()) {
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
            return Statistic.of("Is empty!",
                    "Is empty!",
                    "Is empty!",
                    "Is empty!"
            );
        }
        return Statistic.of("The total number of entries is: " +
                        rsl,
                "The date Of First Entry: " + this.dateOfFirstEntry().getTime(),
                "The date Of Last Entry: " + this.dateOfLastEntry().getTime(),
                "The Id Of Last Entity: " + this.findIdLastEntity()
        );
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

    //+- В запросах нет возможности выбрать сортировку и постраничный вывод
//(сортировка в запросах жестко задана на уровне кода)
    public List<Car> findByRequestParam(Long id,
                                        String color,
                                        int year,
                                        String sort) {//Desc, Asc
        var idOpt = carRepository.findById(id);
        var colorOpt = carRepository.findAllByColor(color);
        var yearOpt = carRepository.findAllByYear(year);
        var sortOpr = sort;

        return null;
    }

//    private List<Car> sortDEscA(String sort) {
//        if (sort.equals(Desc)) {
//            return carRepository.s
//        }
//        return
//    }
}
