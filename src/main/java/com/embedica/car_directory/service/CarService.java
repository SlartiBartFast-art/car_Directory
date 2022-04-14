package com.embedica.car_directory.service;

import com.embedica.car_directory.model.Car;
import com.embedica.car_directory.repository.CarRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    public Map<Boolean, Car> save(Car car) {
        Map<Boolean, Car> carBooleanMap = new HashMap<>();
        Optional<Car> rsl = carRepository.findCarByNumberAndMarkAndColorAndYear(
                car.getNumber(),
                car.getMark(),
                car.getColor(),
                car.getYear()
        );
        if (rsl.isEmpty()) {
            carBooleanMap.put(true, carRepository.save(car));
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
//        Calendar calendar = Calendar.getInstance();
//
//        System.out.println("Convert " + calendar);

//        var id = carRepository.countAllById();
        var count = carRepository.count(); // общее колл-во
        var car = carRepository.findById(Math.toIntExact(count));
        if (car.isPresent()) {
            Calendar date = car.get().getCalendar();
//            System.out.println("car.get().getCalendar() " + car.get().getCalendar());
//            calendar.setTime(date);
//            System.out.println("CALENDAR  " + date);
//            return date.toString();
            return date;
        }
        return null; //todo
    }

    public Calendar dateOfFirstEntry() throws ParseException {
        var count = carRepository.count();
        if (count != 0) {
            var car = carRepository.findById(1);
            if (car.isPresent()) {
                System.out.println("dateOfFirstEntry() -> " + car.get().getCalendar());
                return car.get().getCalendar();
            }
        }
        return whenDateIsEmpty();
    }

    private Calendar whenDateIsEmpty() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-01-2015 10:20:56";
        Date date = sdf.parse(dateInString);
        calendar.setTime(date);
        return calendar;
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

    public List<Car> orderByYear() {
        return carRepository.findCarByYearOrderByYear();
    }
}
