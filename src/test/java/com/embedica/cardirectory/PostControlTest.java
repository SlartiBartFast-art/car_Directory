package com.embedica.cardirectory;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.CarDto;

import com.embedica.cardirectory.model.ColorDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);

        ColorDto color1 = new ColorDto(2L, "Black");
        CarDto carDto1 = new CarDto(2, "A111BA177Rus", "DODGE CHALLENGER GR500", color1, 2017);

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        testRestTemplate.postForEntity(apiId, carDto, Car.class);
        testRestTemplate.postForEntity(apiId, carDto1, Car.class);

        testRestTemplate.delete(apiId + "2");
        ResponseEntity<Car> response3 = testRestTemplate.
                getForEntity(apiId + "2",
                        Car.class);
        Assertions.assertEquals(response3.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
