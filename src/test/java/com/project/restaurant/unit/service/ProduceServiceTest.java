package com.project.restaurant.unit.service;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.adapters.serviceimpl.ProduceServiceImpl;
import com.project.restaurant.domain.repository.ProduceRepository;
import com.project.restaurant.domain.service.ProduceService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.project.restaurant.util.Utility.PRODUCE_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ProduceServiceTest {
    private final ProduceRepository produceRepository = mock();
    private final ProduceService produceService = new ProduceServiceImpl(produceRepository);

    @Test
    void testGetProduceById(){
        when(produceRepository.findById(PRODUCE_LIST.get(0).getId())).thenReturn(Optional.ofNullable(PRODUCE_LIST.get(0)));

        assertThat(produceService.getProduceById(PRODUCE_LIST.get(0).getId())).isEqualTo(PRODUCE_LIST.get(0));

    }
    @Test
    void testGetProduceByIdWhenIdIsInvalid(){
        when(produceRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> produceService.getProduceById(100)).isInstanceOf(NoSuchElementException.class);
    }
    @Test
    void testGetAllProduces(){
        when(produceRepository.findAll()).thenReturn(PRODUCE_LIST);

        assertThat(produceService.getAllProduces()).isEqualTo(PRODUCE_LIST);
    }
    @Test
    void testDeleteProduceById(){
        produceService.deleteProduceById(10);

        verify(produceRepository).deleteById(10);
    }
    @Test
    void testAddProduce(){
        when(produceRepository.save(any())).thenReturn(PRODUCE_LIST.get(0));

        assertThat(produceService.addProduce(PRODUCE_LIST.get(0))).isEqualTo(PRODUCE_LIST.get(0));
    }
}
