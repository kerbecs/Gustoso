package com.project.restaurant.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProduceDto {
    private ProduceDto produce;

    private Double cost;
}
