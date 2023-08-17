package com.project.restaurant.domain.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyPasswordValidator implements ConstraintValidator<ModifyPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null)
            return true;
        if (password.isEmpty())
            return true;
        Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,60}$");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
