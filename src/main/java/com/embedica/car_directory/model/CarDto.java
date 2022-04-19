package com.embedica.car_directory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

/**
 * Car DTO Object
 */
@Data
@NoArgsConstructor
public class CarDto {

    private int id;

    @NotBlank(message = "Number must be not empty")
    private String number;
    @NotBlank(message = "Mark must be not empty")
    private String mark;
    @NotBlank(message = "Color must be not empty")
    private String color;
    @Min(value = 1890, message = "Year must be more than 1980")
    private int year;

    private Calendar calendar;
}
