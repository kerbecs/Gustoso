package com.project.restaurant.domain.service;

import com.project.restaurant.domain.entity.Produce;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProduceService {
    Produce getProduceById(int id);
    List<Produce> getAllProduces();
    Produce addProduce(Produce produce);
    void deleteProduceById(int id);
}
