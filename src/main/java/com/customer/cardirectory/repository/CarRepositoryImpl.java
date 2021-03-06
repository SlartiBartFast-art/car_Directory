package com.customer.cardirectory.repository;

import com.customer.cardirectory.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepositoryImpl extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    /**
     * Find using color parameter
     * найти по цвету
     *
     * @param colorId color Car entiry
     * @return List<Car>
     */
    @Query("select u from Car as u where u.color.coloring = ?1")
    List<Car> findAllByColor(String colorId);

    /**
     * Find using color parameter and int year parameter
     * найти по году и цвету
     *
     * @param year Car obj
     * @return List<Car>
     */
    @Query("select u from Car as u where u.year = ?1 and u.color.coloring = ?2")
    List<Car> findAllByYearAndColor(int year, String color);

    /**
     * Find using moreThan year parameter
     *
     * @param year int
     * @return List<Car>
     */
    @Query("select u from Car as u where u.year > ?1")
    List<Car> findAllByYear(int year);


    /**
     * Resultset order by year Object
     *
     * @return List<Car>
     */
    @Query("select u from Car as u order by u.year")
    List<Car> findCarByYearOrderByYear();


    /**
     * Find date when was created last entity
     *
     * @return Optional<Car>
     */
    Optional<Car> findFirstByOrderByCalendarDesc();

    /**
     * Find date when was created first entity
     *
     * @return Optional<Car>
     */
    Optional<Car> findFirstByOrderByCalendarAsc();

}
