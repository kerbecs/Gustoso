package com.project.restaurant.domain.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDto {
    private Integer userId;
    Integer[] orderedProduceList;
}
