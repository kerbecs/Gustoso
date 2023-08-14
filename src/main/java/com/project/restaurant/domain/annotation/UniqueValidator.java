package com.project.restaurant.domain.annotation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {
    private final EntityManager entityManager;
    private Unique unique;
    @Override
    public void initialize(Unique constraintAnnotation) {
        unique = constraintAnnotation;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String query = String.format("SELECT count(*) FROM %s WHERE %s = ?1",unique.table(),unique.column());
        Long result = (Long) entityManager.createQuery(query).setParameter(1,s).getSingleResult();

        return result==0;

    }
}
