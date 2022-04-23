package com.embedica.car_directory.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data

public class ColorDto {

    private Long id;
    @NotBlank(message = "Coloring must be not empty")
    private String coloring;
}
