package com.icap.icap.commons.utilities.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;


public class Adult18LocalDateTimeValidator implements ConstraintValidator<Adult18, LocalDateTime> {

    static final int ZIM_ADULT_AGE = 18;

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {

        if(isNull(value)){
            return true;
        }

        return value.isBefore(LocalDateTime.now().minusYears(ZIM_ADULT_AGE));

    }
}
