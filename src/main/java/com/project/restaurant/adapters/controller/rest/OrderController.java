package com.project.restaurant.adapters.controller.rest;

import com.project.restaurant.domain.dto.OrderDto;
import com.project.restaurant.domain.facade.OrderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderFacade orderFacade;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderFacade.getAllOrders());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") int id){
        return ResponseEntity.ok(orderFacade.getOrderById(id));
    }
    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@PathVariable("userId") int userId){
        return ResponseEntity.ok(orderFacade.getOrdersByUserId(userId));
    }
}
