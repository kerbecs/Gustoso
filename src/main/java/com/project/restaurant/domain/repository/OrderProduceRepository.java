package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.OrderProduce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProduceRepository extends JpaRepository<OrderProduce, OrderProduce.OrderProduceId> {
}
