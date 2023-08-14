package com.project.restaurant.domain.repository;


import com.project.restaurant.domain.entity.UserDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDescriptionRepository extends JpaRepository<UserDescription, Integer> {
}
