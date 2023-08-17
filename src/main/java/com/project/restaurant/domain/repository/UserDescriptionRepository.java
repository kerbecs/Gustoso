package com.project.restaurant.domain.repository;


import com.project.restaurant.domain.entity.UserDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserDescriptionRepository extends JpaRepository<UserDescription, Integer> {
    @Query("DELETE FROM UserDescription ")
    @Modifying
    @Transactional
    void deleteAll();
}
