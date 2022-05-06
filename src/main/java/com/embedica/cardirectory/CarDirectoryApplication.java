package com.embedica.cardirectory;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarDirectoryApplication {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public TestRestTemplate initTest() {
        return new TestRestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CarDirectoryApplication.class, args);
    }

}
