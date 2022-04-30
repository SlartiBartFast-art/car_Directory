package com.embedica.cardirectory;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.CarDto;

import com.embedica.cardirectory.model.ColorDto;

import com.embedica.cardirectory.usercriteria.SearchCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PostControlTest {

    @Test
    public void whenAddCar() {
        String apiId = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Car> response = testRestTemplate.
                postForEntity(apiId,
                        carDto, Car.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void whenAddCarThanGetById() {
        String apiId = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.
                postForEntity(apiId,
                        carDto, Car.class);

        ResponseEntity<Car> response = testRestTemplate.
                getForEntity(apiId + "1", Car.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenCreateThanDelete() {
        String apiId = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(0L, "Black");
        CarDto carDto = new CarDto(0, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);

        ColorDto color1 = new ColorDto(0L, "Black");
        CarDto carDto1 = new CarDto(0, "A111BA177Rus", "DODGE CHALLENGER GR500", color1, 2017);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.postForEntity(apiId, carDto, Car.class);
        testRestTemplate.postForEntity(apiId, carDto1, Car.class);

        testRestTemplate.delete(apiId + "2");
        ResponseEntity<Car> response3 = testRestTemplate.
                getForEntity(apiId + "2",
                        Car.class);
      /*  var carList = testRestTemplate.exchange(
                apiId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();
        System.out.println("RETU -> " + carList);*/
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    }

    @Test
    public void whenFindByMoreThanYear() {
        String apiYear = "http://localhost:8080/car/";
        String apiId = "http://localhost:8080/car/findByMoreThanYear/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.
                postForEntity(apiYear,
                        carDto, Car.class);
        var responseList = testRestTemplate.exchange(
                apiId + 2016,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();

        Assertions.assertEquals(responseList.get(0).getMark(), carDto.getMark());
    }

    @Test
    public void whenFindWhenOrderByYear() {
        String apiYear = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        ColorDto color1 = new ColorDto(2L, "Black");
        CarDto carDto1 = new CarDto(2, "A587BA177Rus", "DODGE CHALLENGER GR500", color1, 2016);
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.
                postForEntity(apiYear,
                        carDto, Car.class);
        testRestTemplate.
                postForEntity(apiYear,
                        carDto1, Car.class);
        var responseList = testRestTemplate.exchange(
                apiYear,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();

        Assertions.assertEquals(responseList.get(0).getMark(), carDto1.getMark());
    }

    @Test
    public void whenFindByColor() {
        String apiYear = "http://localhost:8080/car/findByColor/";
        String apiId = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        ColorDto color1 = new ColorDto(2L, "Red");
        CarDto carDto1 = new CarDto(2, "A587BA177Rus", "Grand JS GR500", color1, 2016);
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.
                postForEntity(apiId,
                        carDto, Car.class);
        testRestTemplate.
                postForEntity(apiId,
                        carDto1, Car.class);
        var responseList = testRestTemplate.exchange(
                apiYear + "Red",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();

        Assertions.assertEquals(responseList.get(0).getMark(), carDto1.getMark());
    }

    @Test
    public void whenLastDate() {
        String apiLast = "http://localhost:8080/car/lastDate";
        String apiId = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        ColorDto color1 = new ColorDto(2L, "Red");
        CarDto carDto1 = new CarDto(2, "A587BA177Rus", "Grand JS GR500", color1, 2016);
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.
                postForEntity(apiId,
                        carDto, Car.class);
        testRestTemplate.
                postForEntity(apiId,
                        carDto1, Car.class);
        var responseList = testRestTemplate.exchange(
                apiId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();

        var result = testRestTemplate.
                getForEntity(apiLast, Calendar.class);

        Assertions.assertEquals(responseList.get(1).getCalendar(), result.getBody());
    }

    @Test
    public void whenFindByYearAndColor() {
        String apiYear = "http://localhost:8080/car/findByYearAndColor?";
        String apiId = "http://localhost:8080/car/";

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        ColorDto color1 = new ColorDto(2L, "Red");
        CarDto carDto1 = new CarDto(2, "A587BA177Rus", "Grand JS GR500", color1, 2016);
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.
                postForEntity(apiId,
                        carDto, Car.class);
        testRestTemplate.
                postForEntity(apiId,
                        carDto1, Car.class);
        var responseList = testRestTemplate.exchange(
                apiYear + "year=2016&color=Red",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                }
        ).getBody();

        Assertions.assertEquals(responseList.get(0).getMark(), carDto1.getMark());
    }
}
