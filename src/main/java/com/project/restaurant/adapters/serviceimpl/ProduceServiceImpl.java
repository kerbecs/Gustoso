package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.domain.entity.Produce;
import com.project.restaurant.domain.repository.ProduceRepository;
import com.project.restaurant.domain.service.ProduceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduceServiceImpl implements ProduceService {
    private final ProduceRepository produceRepository;

    @Override
    @Cacheable(value = "produces", key = "#id")
    public Produce getProduceById(int id) {
        return produceRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Produce with id %d does not exists", id)));
    }

    @Override
    @Cacheable("produces")
    public List<Produce> getAllProduces() {
        return produceRepository.findAll();
    }

    @Override
    @CacheEvict(value = "produces", allEntries = true)
    public void deleteProduceById(int id) {
        produceRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "produces", allEntries = true)
    public Produce addProduce(Produce produce) {
        return produceRepository.save(produce);
    }


}
