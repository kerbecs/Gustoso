package com.project.restaurant.domain.repository;

import com.project.restaurant.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User SET password = :newPassword WHERE username = :username")
    void modifyUserPassword(@Param("newPassword") String newPassword,@Param("username") String username);
    @Query("DELETE FROM User ")
    @Modifying
    @Transactional
    void deleteAll();


    @Transactional
    @Modifying
    @Query("UPDATE User SET isActive = 1 WHERE username = :username")
    void activateUser(String username);
}
