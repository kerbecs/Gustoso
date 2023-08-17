package com.project.restaurant.domain.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderProduceDto {
    private ProduceDto produce;

    private Double cost;
}
