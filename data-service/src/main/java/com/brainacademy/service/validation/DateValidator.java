package com.brainacademy.service.validation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;

public class DateValidator implements ConstraintValidator<IsDate, String> {

    private boolean optional;
    private String pattern;

    @Override
    public void initialize(IsDate constraintAnnotation) {
        optional = constraintAnnotation.optional();
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean valid = false;
        if (value != null) {
            try {
                DateUtils.parseDate(value, pattern);
                valid = true;
            } catch (ParseException e) {
                valid = false;
            }
        }

        return optional ? (valid || (StringUtils.isEmpty(value))) : valid;
    }
}
