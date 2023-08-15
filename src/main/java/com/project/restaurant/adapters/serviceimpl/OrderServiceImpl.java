package com.project.restaurant.adapters.serviceimpl;

import com.project.restaurant.adapters.exception.NoSuchElementException;
import com.project.restaurant.domain.entity.Order;
import com.project.restaurant.domain.repository.OrderRepository;
import com.project.restaurant.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrderByUserId(int id) {
        return orderRepository.findByUserId(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Order with id %s does not exist",id)));
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}
