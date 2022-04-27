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
    public void whenAddCar() {
        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        carDto.setMark("Buik");
        String fooResourceUrl = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Car> response = testRestTemplate.
                postForEntity(fooResourceUrl,
                        carDto, Car.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void whenCreateThanDelete() {
        ColorDto color = new ColorDto(1L, "Black");
        CarDto carDto = new CarDto(1, "A587BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        carDto.setMark("Buik");
        String fooResourceUrl = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<Car> response = testRestTemplate.
                postForEntity(fooResourceUrl,
                        carDto, Car.class);
        ColorDto color1 = new ColorDto(2L, "Black");
        CarDto carDto1 = new CarDto(2, "A111BA177Rus", "DODGE CHALLENGER GR500", color, 2017);
        carDto.setMark("Buik");
        String fooResourceUrl1 = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate1 = new TestRestTemplate();
        ResponseEntity<Car> response1 = testRestTemplate.
                postForEntity(fooResourceUrl,
                        carDto, Car.class);
        String apiId = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate2 = new TestRestTemplate();

        testRestTemplate2.delete(apiId + "2");
        String apiIdd = "http://localhost:8080/car/";
        TestRestTemplate testRestTemplate3 = new TestRestTemplate();
        ResponseEntity<Car> response3 = testRestTemplate3.
                getForEntity(apiIdd + "2",
                        Car.class);

        Assertions.assertEquals(response3.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
