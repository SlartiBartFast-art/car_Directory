package com.embedica.car_directory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * модель, описывает абстракцию Автомобиль
 */
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Number must be not empty")
    private String number;
    @NotBlank(message = "Mark must be not empty")
    private String mark;
    @NotBlank(message = "Color must be not empty")
    private String color;
    @Min(value = 1890, message = "Year must be more than 1980")
    private int year;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Yekaterinburg")
    private Calendar calendar;

    public Car() {
    }

    public static Car of(String number, String mark, String color, int year) {
        Car car = new Car();
        car.number = number;
        car.mark = mark;
        car.color = color;
        car.year = year;
        return car;
    }
}
