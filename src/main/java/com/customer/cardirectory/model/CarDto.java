package com.customer.cardirectory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    
    private int id;
    @NotBlank(message = "Number must be not empty")
    private String number;
    @NotBlank(message = "Mark must be not empty")
    private String mark;

    private ColorDto color;
    @Min(value = 1890, message = "Year must be more than 1890")
    private int year;
    
      public static CarDto of(String number, String mark, ColorDto color, int year) {
        CarDto carDto = new CarDto();
        carDto.number = number;
        carDto.mark = mark;
        carDto.color = color;
        carDto.year = year;
        return carDto;
    }
}
