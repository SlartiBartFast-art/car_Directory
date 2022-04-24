package com.embedica.cardirectory.model;

import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class ColorDto {

    private Long id;
    @NotBlank(message = "Coloring must be not empty")
    private String coloring;
}
