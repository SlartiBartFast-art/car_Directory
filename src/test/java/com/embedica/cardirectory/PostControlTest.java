package com.embedica.cardirectory;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.CarDto;

import com.embedica.cardirectory.model.ColorDto;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PostControlTest {

    @Test
    public void givenСarDto_whenGetCar() {
        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        carDto.setMark("Buik");
        String FOO_RESOURCE_URL = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Car> response = testRestTemplate.
                postForEntity(FOO_RESOURCE_URL,
                        carDto, Car.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void givenСarDto_whenDelete() {
        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        carDto.setMark("Buik");
        String FOO_RESOURCE_URL = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Car> response = testRestTemplate.
                postForEntity(FOO_RESOURCE_URL,
                        carDto, Car.class);
        ColorDto color1 = new ColorDto(2L, "Black");
        CarDto carDto1 = new CarDto(2, "A111BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        carDto.setMark("Buik");
        String FOO_RESOURCE_URL1 = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate1 = new TestRestTemplate();
        ResponseEntity<Car> response1 = testRestTemplate.
                postForEntity(FOO_RESOURCE_URL,
                        carDto, Car.class);
        String API_ID = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate2 = new TestRestTemplate();

        testRestTemplate2.delete(API_ID + "2");
        String API_IDD = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate3 = new TestRestTemplate();
        ResponseEntity<Car> response3 = testRestTemplate3.
                getForEntity(API_IDD + "2",
                        Car.class);

        Assertions.assertEquals(response3.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}