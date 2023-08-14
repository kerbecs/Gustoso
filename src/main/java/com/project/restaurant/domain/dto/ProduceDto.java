package com.project.restaurant.domain.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduceDto {
    private Integer id;

    @Pattern(regexp = "^[A-Za-z0-9\\s\'\\-]{2,25}$")
    private String name;

    @Size(max = 150)
    private String ingredients;

    @Pattern(regexp = "")
    @Min(value = 0)
    private String weight;

    @Min(value = 0)
    @Pattern(regexp = "")
    private Double price;

    private String image;
}
