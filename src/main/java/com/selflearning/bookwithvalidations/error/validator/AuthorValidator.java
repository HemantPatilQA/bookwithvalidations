package com.selflearning.bookwithvalidations.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AuthorValidator implements ConstraintValidator<Author, String> {
    List<String> authors = Arrays.asList("Santideva", "Marie Kondo", "Martin Fowler", "Hemant");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return authors.contains(value);
    }
}
