package com.project.restaurant.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "user_description")
public class UserDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "firstname")
    private String firstName;

    @Column(nullable = false, name = "lastname")
    private String lastName;

    @Column(nullable = false,unique = true, updatable = false)
    private String email;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(name = "user_description")
    private String description;
    private String job;

    @Column(updatable = false, insertable = false)
    private Integer orders;

}
