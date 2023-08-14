package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.Produce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduceRepository extends JpaRepository<Produce, Integer> {
}
