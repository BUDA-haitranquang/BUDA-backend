package com.higroup.Buda.Validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomizeValidator implements ConstraintValidator<CustomizeConstraint, String> {

    List<String> constraints = Arrays.asList("Constraint 1", "Constraint 2", "Constraint 3", "Constraint 4");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return constraints.contains(value);

    }
}
