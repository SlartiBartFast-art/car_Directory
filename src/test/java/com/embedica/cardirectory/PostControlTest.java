package com.embedica.cardirectory;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.CarDto;

import com.embedica.cardirectory.model.ColorDto;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PostControlTest {

    private static final String API_ID = "http://localhost:8080/car/";
    private static final String API_YEAR = "http://localhost:8080/car/findByMoreThanYear/";
    private static final String API_COLOR = "http://localhost:8080/car/findByColor/";
    private static final String API_YEARANDCOLOR = "http://localhost:8080/car/findByYearAndColor?";
    private static final String API_DELETE = "http://localhost:8080/car/all";

    @Autowired
    private TestRestTemplate testRestTemplate;

    private CarDto carDto;
    private CarDto carDto1;
    private Long rsl;
    private ResponseEntity<Car> car;

    @BeforeEach
    void init() {
        ColorDto color = new ColorDto(0L, "Red");
        carDto = new CarDto(0, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        ColorDto color1 = new ColorDto(0L, "Black");
        carDto1 = new CarDto(0, "A587BA177Rus", "Grand JS GR500", color1, 2016);
        testRestTemplate.
                postForEntity(API_ID,
                        carDto, Car.class);
        car = testRestTemplate.
                postForEntity(API_ID,
                        carDto1, Car.class);
        rsl = car.getBody().getId();
       /* var carList = testRestTemplate.exchange(
                API_ID,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();
        System.out.println("RETURN -> " + carList);*/
    }

    @AfterEach
    void dispose() {
        testRestTemplate.delete(API_DELETE);
    }

    @Test
    public void whenAddCar() {
        Assertions.assertEquals(HttpStatus.CREATED, car.getStatusCode());
    }

    @Test
    public void whenAddCarThanGetById() {
        ResponseEntity<Car> response = testRestTemplate.
                getForEntity(API_ID + rsl, Car.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenCreateThanDelete() {
        testRestTemplate.delete(API_ID + rsl);
        ResponseEntity<Car> response3 = testRestTemplate.
                getForEntity(API_ID + rsl,
                        Car.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    }

    @Test
    public void whenFindByMoreThanYear() {
        var responseList = testRestTemplate.exchange(
                API_YEAR + 2016,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();
        Assertions.assertEquals(responseList.get(0).getMark(), carDto.getMark());
    }

    @Test
    public void whenFindWhenOrderByYear() {
        var responseList = testRestTemplate.exchange(
                API_ID,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();
        Assertions.assertEquals(responseList.get(1).getMark(), carDto1.getMark());
    }

    @Test
    public void whenFindByColor() {
        var responseList = testRestTemplate.exchange(
                API_COLOR + "Red",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();
        Assertions.assertEquals(responseList.get(0).getMark(), carDto.getMark());
    }

    @Test
    public void whenFindByYearAndColor() {
        var responseList = testRestTemplate.exchange(
                API_YEARANDCOLOR + "year=2017&color=Red",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();
        Assertions.assertEquals(responseList.get(0).getMark(), carDto.getMark());
    }
}
