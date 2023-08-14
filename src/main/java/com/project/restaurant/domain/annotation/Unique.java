package com.project.restaurant.domain.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Unique {
    String value() default "";
    String message() default "";
    String table() default "";
    String column() default "";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
