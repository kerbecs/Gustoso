package com.project.restaurant.domain.facade;

import com.project.restaurant.domain.dto.ProduceDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ProduceFacade {
    List<ProduceDto> getAllProduces();
    ProduceDto getProduceById(int id);
    ProduceDto addProduce(ProduceDto produceDto);
    ProduceDto updateProduce(ProduceDto produceDto);
}
