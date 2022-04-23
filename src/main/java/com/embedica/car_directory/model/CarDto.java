package com.embedica.car_directory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;

@Data
@NoArgsConstructor
public class CarDto {
    
    private int id;
    @NotBlank(message = "Number must be not empty")
    private String number;
    @NotBlank(message = "Mark must be not empty")
    private String mark;

    private ColorDto color;
    @Min(value = 1890, message = "Year must be more than 1890")
    private int year;
    
    private Calendar calendar;
}
