package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.Produce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProduceRepository extends JpaRepository<Produce, Integer> {
    @Query("DELETE FROM Produce ")
    @Modifying
    @Transactional
    void deleteAll();
}
