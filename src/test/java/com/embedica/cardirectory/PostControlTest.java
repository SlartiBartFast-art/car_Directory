package com.embedica.cardirectory;

import com.embedica.cardirectory.model.Car;
import com.embedica.cardirectory.model.CarDto;

import com.embedica.cardirectory.model.ColorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
}
