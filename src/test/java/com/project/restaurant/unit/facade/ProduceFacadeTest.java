package com.project.restaurant.unit.facade;


import com.project.restaurant.adapters.facadeimpl.ProduceFacadeImpl;
import com.project.restaurant.domain.dto.ProduceDto;
import com.project.restaurant.domain.entity.Produce;
import com.project.restaurant.domain.facade.ProduceFacade;
import com.project.restaurant.domain.mapstruct.ProduceMapper;
import com.project.restaurant.domain.service.ProduceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.project.restaurant.util.Utility.PRODUCE_DTO_LIST;
import static com.project.restaurant.util.Utility.PRODUCE_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProduceFacadeTest {
    private final ProduceService produceService = mock();
    private final ProduceMapper produceMapper = Mappers.getMapper(ProduceMapper.class);
    private final ProduceFacade produceFacade = new ProduceFacadeImpl(produceService,produceMapper);

    @Test
    void testGetAllProduces(){
        when(produceService.getAllProduces()).thenReturn(PRODUCE_LIST);

        assertThat(produceFacade.getAllProduces()).isEqualTo(PRODUCE_DTO_LIST);
    }
    @Test
    void testGetProduceById(){
        when(produceService.getProduceById(1)).thenReturn(PRODUCE_LIST.get(0));

        assertThat(produceFacade.getProduceById(1)).isEqualTo(PRODUCE_DTO_LIST.get(0));
    }
    @Test
    void addProduce(){
        ProduceDto returned = produceFacade.addProduce(PRODUCE_DTO_LIST.get(0));
        ArgumentCaptor<Produce> argumentCaptor = ArgumentCaptor.forClass(Produce.class);

        verify(produceService).addProduce(argumentCaptor.capture());

        Produce created = argumentCaptor.getValue();

        assertAll(
                () -> assertThat(created.getId()).isNull(),
                () -> assertThat(returned).isEqualTo(PRODUCE_DTO_LIST.get(0))
        );

    }

    @Test
    void updateProduce(){
        ProduceDto returned = produceFacade.updateProduce(PRODUCE_DTO_LIST.get(0));
        ArgumentCaptor<Produce> argumentCaptor = ArgumentCaptor.forClass(Produce.class);

        verify(produceService).addProduce(argumentCaptor.capture());

        Produce created = argumentCaptor.getValue();

        assertAll(
                () -> assertThat(created.getId()).isNotNull(),
                () -> assertThat(returned).isEqualTo(PRODUCE_DTO_LIST.get(0))
        );

    }
}
