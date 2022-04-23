package com.embedica.car_directory.repository;

import com.embedica.car_directory.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepositoryImpl extends JpaRepository<Car, Long>, CustomCarRepository {

    boolean existsById(Long id);

    Page<Car> findAll(Pageable pageable, Sort sort);

    /**
     * Find exact match to Entity model parameters
     *
     * @param number Car obj
     * @param mark   Car obj
     * @param color  Car obj
     * @param year   Car obj
     * @return Optional<Car>
     */
    @Query("select u from Car as u where u.number = ?1 and u.mark = ?2 and u.color = ?3 and u.year = ?4")
    Optional<Car> findCarByNumberAndMarkAndColorAndYear(String number, String mark, String color, int year);

    /**
     * Find using color parameter
     * найти по цвету
     *
     * @param color Car obj
     * @return List<Car>
     */
    @Query("select u from Car as u where u.color = ?1")
    List<Car> findAllByColor(String color);

    /**
     * Find using color parameter and int year parameter
     * найти по году и цвету
     *
     * @param year Car obj
     * @return List<Car>
     */
    @Query("select u from Car as u where u.year = ?1 and u.color = ?2")
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
