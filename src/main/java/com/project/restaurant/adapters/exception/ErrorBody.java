package com.project.restaurant.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorBody {
    private String message;
    private String path;
    private Integer status;
    private Long timestamp;
}
