package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("FROM Order WHERE user.id = :userId")
    List<Order> findByUserId(@Param("userId") Integer userId);
    @Query("DELETE FROM Order")
    @Modifying
    @Transactional
    void deleteAll();
}
