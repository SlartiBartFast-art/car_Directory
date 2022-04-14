package com.embedica.car_directory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * модель, описывает абстракцию Автомобиль
 */
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String mark;
    private String color;
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
