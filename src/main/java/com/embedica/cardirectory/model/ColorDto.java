package com.embedica.cardirectory.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ColorDto {

    private Long id;
    @NotBlank(message = "Coloring must be not empty")
    private String coloring;
}
