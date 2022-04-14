package com.embedica.car_directory.repository;

import com.embedica.car_directory.model.Car;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository
 */
public interface CarRepository extends CrudRepository<Car, Integer> {

    @Query("select u from Car as u where u.number = ?1 and u.mark = ?2 and u.color = ?3 and u.year = ?4")
    Optional<Car> findCarByNumberAndMarkAndColorAndYear(String number, String mark, String color, int year);

    /**
     * найти по цвету
     *
     * @param color
     * @return
     */
    @Query("select u from Car as u where u.color = ?1")
    List<Car> findAllByColor(String color);

    /**
     * найти по году и цвету
     *
     * @param year
     * @return
     */
    @Query("select u from Car as u where u.year = ?1 and u.color = ?2")
    List<Car> findAllByYearAndColor(int year, String color);

    /**
     * moreThan year
     *
     * @param year int
     * @return List<Car>
     */
    @Query("select u from Car as u where u.year > ?1")
    List<Car> findAllByYear(int year);

    /**
     * количество записей
     *
     * @return
     */
    @Query("select count(u.id) from Car as u")
    public int countAllById();

    /**
     * дата добавления первой записи
     *
     * @return Car
     */
    @Query("select u from Car as u where u.id = 1")
    public Car findFirstByCalendar();

    //Сортировка по атрибутам при запросе списка;U
    @Query("select u from Car as u order by u.year")
    public List<Car> findCarByYearOrderByYear();
}
