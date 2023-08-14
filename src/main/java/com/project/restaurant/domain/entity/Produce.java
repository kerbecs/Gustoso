package com.project.restaurant.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table
public class Produce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private Double price;

    private String image;
}
