package com.project.restaurant.adapters.facadeimpl;

import com.project.restaurant.domain.dto.ProduceDto;
import com.project.restaurant.domain.entity.Produce;
import com.project.restaurant.domain.facade.ProduceFacade;
import com.project.restaurant.domain.mapstruct.ProduceMapper;
import com.project.restaurant.domain.service.ProduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduceFacadeImpl implements ProduceFacade {
    private final ProduceService produceService;
    private final ProduceMapper produceMapper;

    @Override
    public List<ProduceDto> getAllProduces() {
        return produceService.getAllProduces()
                .stream()
                .map(produceMapper::produceToProduceDto)
                .toList();
    }

    @Override
    public ProduceDto getProduceById(int id) {
        return produceMapper.produceToProduceDto(produceService.getProduceById(id));
    }

    @Override
    public ProduceDto addProduce(ProduceDto produceDto) {
        Produce produce = produceMapper.produceDtoToProduce(produceDto);
        produce.setId(null);
        produceService.addProduce(produce);

        return produceMapper.produceToProduceDto(produce);
    }

    @Override
    public ProduceDto updateProduce(ProduceDto produceDto) {
        produceService.getProduceById(produceDto.getId());
        Produce produce = produceMapper.produceDtoToProduce(produceDto);
        produceService.addProduce(produce);

        return produceMapper.produceToProduceDto(produce);
    }
}
