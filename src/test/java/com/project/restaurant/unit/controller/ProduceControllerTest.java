package com.project.restaurant.unit.controller;

import com.project.restaurant.adapters.controller.rest.ProduceController;
import com.project.restaurant.domain.facade.ProduceFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.project.restaurant.util.Utility.PRODUCE_DTO_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProduceControllerTest {
    private final ProduceFacade produceFacade = Mockito.mock();
    private final ProduceController produceController = new ProduceController(produceFacade);

    @Test
    void testGetAllProduces(){
        when(produceFacade.getAllProduces()).thenReturn(PRODUCE_DTO_LIST);

        assertThat(produceController.getAllProduces()).isEqualTo(ResponseEntity.ok(PRODUCE_DTO_LIST));
    }
    @Test
    void testGetProduceById(){
        when(produceFacade.getProduceById(1)).thenReturn(PRODUCE_DTO_LIST.get(0));

        assertThat(produceController.getProduceById(1)).isEqualTo(ResponseEntity.ok(PRODUCE_DTO_LIST.get(0)));
    }
    @Test
    void testAddProduce() {
        when(produceFacade.addProduce(PRODUCE_DTO_LIST.get(0))).thenReturn(PRODUCE_DTO_LIST.get(0));

        assertThat(produceController.addProduce(PRODUCE_DTO_LIST.get(0))).isEqualTo(new ResponseEntity(PRODUCE_DTO_LIST.get(0), HttpStatus.CREATED));
    }
    @Test
    void testUpdateProduce(){
        when(produceFacade.updateProduce(PRODUCE_DTO_LIST.get(0))).thenReturn(PRODUCE_DTO_LIST.get(0));

        assertThat(produceController.updateProduce(PRODUCE_DTO_LIST.get(0))).isEqualTo(new ResponseEntity(PRODUCE_DTO_LIST.get(0), HttpStatus.CREATED));
    }

}
