package com.embedica.car_directory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

/**
 * модель, описывает абстракцию Автомобиль
 */
@Data
@Entity
@NoArgsConstructor
public class Car {
    public static final Car EMPTY = new Car();
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String mark;

    @OneToOne
    @JoinColumn(name = "color_id")
    private Color color;

    private int year;
    
    private Calendar calendar;

    private Calendar modified;

    @PrePersist
    void onCreate() {
        this.setCalendar(Calendar.getInstance());
        this.setModified(Calendar.getInstance());
    }
    @PreUpdate
    void onUpdate() {
        this.setModified(Calendar.getInstance());
    }
    

    public static Car of(String number, String mark, Color color, int year) {
        Car car = new Car();
        car.number = number;
        car.mark = mark;
        car.color = color;
        car.year = year;
        return car;
    }
}
