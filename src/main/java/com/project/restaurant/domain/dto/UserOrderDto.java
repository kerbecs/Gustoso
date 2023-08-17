package com.project.restaurant.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDto {
    private Integer userId;
    Integer[] orderedProduceList;
}
