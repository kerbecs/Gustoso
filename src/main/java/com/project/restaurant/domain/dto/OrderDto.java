package com.project.restaurant.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderDto {
    @EqualsAndHashCode.Exclude
    private Integer id;
    @Min(value = 0)
    private Double cost;

    @PastOrPresent
    @EqualsAndHashCode.Exclude
    private LocalDateTime date;

    @NotNull
    private UserDto user;

    private List<OrderProduceDto> orderProduceList;

}
