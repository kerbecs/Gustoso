package com.project.restaurant.adapters.controller.rest;

import com.project.restaurant.domain.dto.ProduceDto;
import com.project.restaurant.domain.facade.ProduceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produces")
@RequiredArgsConstructor
public class ProduceController {
    private final ProduceFacade produceFacade;

    @GetMapping
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<List<ProduceDto>> getAllProduces() {
        return ResponseEntity.ok(produceFacade.getAllProduces());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<ProduceDto> getProduceById(@PathVariable int id) {
        return ResponseEntity.ok(produceFacade.getProduceById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProduceDto> addProduce(@RequestBody @Valid ProduceDto produceDto) {
        return new ResponseEntity<>(produceFacade.addProduce(produceDto), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProduceDto> updateProduce(@RequestBody @Valid ProduceDto produceDto) {
        return new ResponseEntity<>(produceFacade.updateProduce(produceDto), HttpStatus.CREATED);
    }
}
