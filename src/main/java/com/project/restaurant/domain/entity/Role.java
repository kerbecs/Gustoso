package com.project.restaurant.domain.entity;

import com.project.restaurant.domain.roles.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "name")
    @Enumerated(value = EnumType.STRING)
    private Roles role;
}
