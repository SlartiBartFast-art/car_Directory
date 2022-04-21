package com.embedica.car_directory.controller;

import com.embedica.car_directory.model.Car;
import com.embedica.car_directory.model.CarDto;
import com.embedica.car_directory.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.List;

@Validated
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    
    private final CarService carService;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private final EntityManager entityManager;
    
    
    /**
     * The getting a list of all records
     *
     * @return List<Car>
     */
    @GetMapping
    public List<Car> findAll() {
        return carService.findAllByOrder();
    }
    
/*    // https://www.baeldung.com/jpa-and-or-criteria-predicates
    @GetMapping // ?di=...&color=...
    public List<Car> findAll(@RequestParam String id,
                             @RequestParam String color,
                             @RequestParam String year,
                             @RequestParam String sort
    ) {
        val criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> itemRoot = criteriaQuery.from(Car.class);
        
        // where car.id = {id} AND car.color = {color}
        if (id != null) criteriaBuilder.equal(itemRoot.get("id"), id);
        if (color != null) criteriaBuilder.equal(itemRoot.get("color"), color);
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }*/
    
    /**
     * Добавление автомобиля
     * Результат операции (успех, ошибка, объект уже существует)
     *
     * @param car Object Car
     * @return ResponseEntity<Car>
     */
    // TODO : ЖОПА 1 - Map<Boolean, Car> - решение отстой, лучше уж:
    //    1 - null || Car
    //    2 - Optional<Car>
    //    3 - Car.EMPTY
    @PostMapping
    public ResponseEntity<Car> add(@Valid @RequestBody CarDto car) {
        var rsl = carService.save(modelMapper.map(car, Car.class));
        if (rsl.containsKey(false)) {
            throw new IllegalArgumentException("The object is already exist!!!.");
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "The object is already exist!!!."
//            );
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
    public ResponseEntity<Calendar> lastCarDate() {
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
    public ResponseEntity<Calendar> firstCarDate() {
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
    // TODO : ЖОПА 2 - Car.EMPTY || (null || Car) || Optional<Car>
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete(@PathVariable("id") @Min(1) int id) {
        var count = carService.findIdLastEntity();
        if (id > count) {
            throw new IllegalArgumentException(
                    "The object id must be correct, object like this id don't exist!");
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "The object id must be correct, object like this id don't exist!");
        }
        var rsl = carService.delete(id);
        
        // TODO : ЖОПА 2 - Car.EMPTY || (null || Car) || Optional<Car>
        return new ResponseEntity<>(rsl,
                rsl.getColor() != null && rsl.getMark() != null ?
                        HttpStatus.OK : HttpStatus.NOT_FOUND);


/*        if (carService.contains(id)) throw new IllegalArgumentException(
                    "The object id must be correct, object like this id don't exist!");
        var rsl = carService.whenRemovedCar(id);
        return rsl.map(it -> new ResponseEntity(it, HttpStatus.OK))
                .orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));*/
    }
    
    /**
     * Database statistics - number of records
     *
     * @return String object
     */
    @GetMapping("/statistics")
    public ResponseEntity<String> statistics() {
        return new ResponseEntity<>(carService.statistic(), HttpStatus.OK);
    }
    
    
    /**
     * Getting a list of entities from the database,
     * by the specified parameter (color)
     *
     * @param color object
     * @return List<Car>
     */
    // TODO : ЖОПА 3 - SET || TABLE + MAGIC VALUE - not const
    @GetMapping("/fndByColor/{color}")
    public List<Car> findAllByColor(@PathVariable("color") String color) {
        if (carService.matches(color).equals("not registered")) {
            throw new IllegalArgumentException(
                    "The color object must be correct!");
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "The color object must be correct.");
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
}
