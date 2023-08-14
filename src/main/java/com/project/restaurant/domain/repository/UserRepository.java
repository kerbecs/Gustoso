package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User SET password = :newPassword WHERE username = :username")
    void modifyUserPassword(String newPassword, String username);
}
